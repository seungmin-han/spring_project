SELECT 
/*
    tbl_group (grp)
 groupSeq: 그룹의 PK
 groupName: 이름
 gorupText: 소개
 grp.memberSeq: 그룹장PK
*/
  grp.groupSeq 
  , groupName 
  , groupText
  , grp.memberSeq 
/*
    joinStatus (jst)
 joinStatusCdText: 가입 상태 코드에 따른 텍스트
 joinStatusCd: 가입 상태 코드 (Y:가입됨, W:대기중, N:미가입)
 memberCount: 해당 그룹의 가입된 사용자들의 수(서브쿼리)
*/
  , CASE 
  	WHEN joinStatusCd = 'Y' 
  	THEN '가입됨' 
  	WHEN joinStatusCd = 'W' 
  	THEN '승인대기' 
  	ELSE NULL 
  END AS joinStatusCdText 
  , joinStatusCd 
  , (
    SELECT 
      COUNT(*) 
    FROM 
      joinStatus 
    WHERE 
      groupSeq = grp.groupSeq 
      AND joinStatusCd = 'Y'
    GROUP BY 
      groupSeq
  ) AS memberCount 
/*
    groupTag (gt)
 groupTags: 해당 그룹의 태그들을 ','로 묶은 텍스트
*/
  , GROUP_CONCAT(DISTINCT groupTagName SEPARATOR ',') AS groupTags
/*
    groupImage (gi)
 groupImageName: 해당 그룹의 이미지 경로
*/
  , groupImageName 
FROM 
  tbl_group AS grp 
/*
    그룹 테이블과 가입 상태 테이블 LEFT JOIN (로그인한 사용자의 가입 상태 데이터)
*/
LEFT JOIN joinStatus AS jst ON grp.groupSeq = jst.groupSeq 
  AND jst.memberSeq = #{memberSeq}
  AND jst.joinStatusDelNy = 0
/*
    그룹 테이블과 그룹 이미지 테이블 LEFT JOIN (그룹에서 현재 사용중인 이미지)
*/
LEFT JOIN groupImage AS gi ON gi.groupSeq = grp.groupSeq
  AND gi.groupImageThumNy = 1
  AND gi.groupImageDelNy = 0
/*
    그룹 테이블과 그룹 태그 테이블 LEFT JOIN (해당 그룹 태그)
*/
LEFT JOIN groupTag AS gt ON gt.groupSeq = grp.groupSeq
WHERE 
/*
    조건 1. (AND)

    해당 그룹이 존재해야 함
*/
  grp.groupDelNy = 0
/*
    조건 2. (AND)

    해당 그룹의 가입 상태에서 코드가 'N'이 아닌 ('Y' or 'W')
    나의 가입 상태가 존재 하지 않아야함. 
*/
    AND NOT EXISTS
    (
      SELECT 
        memberSeq 
      FROM 
        joinStatus 
      WHERE 
	    groupSeq = grp.groupSeq
        AND NOT joinStatusCd = 'N' 
        AND memberSeq = #{memberSeq}
    ) 
/*
    조건 3. (AND)

    아래 조건 중 하나만 성립되어도 참(True)
*/
    AND 
    (
/*
    조건 3-1. (OR)

    해당 그룹의 태그 중 내가 설정한 태그와
    중복되는 데이터가 존재해야 함.

    ex) 내가 설정한 태그: 축구
        그룹에서 설정한 태그: 축구
        인 경우 True
*/
      EXISTS 
      (
  	    SELECT 
  		  memberTagName
  	    FROM
  		  memberTag
  	    WHERE
          memberSeq = #{memberSeq}
  		  AND memberTagName IN 
          (
		    SELECT 
		      groupTagName
			FROM
  	  		  groupTag
   		    WHERE
  			  groupTag.groupSeq = grp.groupSeq
  		  )  	
	  ) 
      OR 
/*
    조건 3-2. (OR)

    해당 그룹의 태그 중 내가 설정한 태그(텍스트)가
    포함되는 데이터가 존재해야 함. 
    
    ex) 내가 설정한 태그: 축구
        그룹에서 설정한 태그: 축구관람
        인 경우 True
*/
		EXISTS 
        (
		  SELECT
            groupTagName
		  FROM
            groupTag
		  WHERE
            groupTagName REGEXP 
            (
			  SELECT 
                GROUP_CONCAT(memberTagName SEPARATOR '|') AS memberTagNames
			  FROM
                memberTag
			  WHERE 
                memberSeq = #{memberSeq}
            )
            AND grouptag.groupSeq = grp.groupSeq
        )
    )
GROUP BY 
  grp.groupSeq


/*===================================================================*/
SELECT 
  grp.groupSeq 
  , groupName 
  , groupText
  , grp.memberSeq 
  , CASE 
  	WHEN joinStatusCd = 'Y' 
  	THEN '가입됨' 
  	WHEN joinStatusCd = 'W' 
  	THEN '승인대기' 
  	ELSE NULL 
  END AS joinStatusCdText 
  , joinStatusCd 
  , (
    SELECT 
      COUNT(*) 
    FROM 
      joinStatus 
    WHERE 
      groupSeq = grp.groupSeq 
      AND joinStatusCd = 'Y'
    GROUP BY 
      groupSeq
  ) AS memberCount 
  , GROUP_CONCAT(DISTINCT groupTagName SEPARATOR ',') AS groupTags
  , groupImageName 
FROM 
  tbl_group AS grp 
LEFT JOIN joinStatus AS jst ON grp.groupSeq = jst.groupSeq 
  AND jst.memberSeq = #{memberSeq}
  AND jst.joinStatusDelNy = 0
LEFT JOIN groupImage AS gi ON gi.groupSeq = grp.groupSeq
  AND gi.groupImageThumNy = 1
  AND gi.groupImageDelNy = 0
LEFT JOIN groupTag AS gt ON gt.groupSeq = grp.groupSeq
WHERE 
  grp.groupDelNy = 0
    AND NOT EXISTS
    (
      SELECT 
        memberSeq 
      FROM 
        joinStatus 
      WHERE 
	    groupSeq = grp.groupSeq
        AND NOT joinStatusCd = 'N' 
        AND memberSeq = #{memberSeq}
    ) 
    AND 
    (
      EXISTS 
      (
  	    SELECT 
  		  memberTagName
  	    FROM
  		  memberTag
  	    WHERE
          memberSeq = #{memberSeq}
  		  AND memberTagName IN 
          (
		    SELECT 
		      groupTagName
			FROM
  	  		  groupTag
   		    WHERE
  			  groupTag.groupSeq = grp.groupSeq
  		  )  	
	  ) 
      OR 
		  EXISTS 
      (
		    SELECT
          groupTagName
		    FROM
          groupTag
		    WHERE
          groupTagName REGEXP 
        (
			    SELECT 
            GROUP_CONCAT(memberTagName SEPARATOR '|') AS memberTagNames
			    FROM
            memberTag
			    WHERE 
            memberSeq = #{memberSeq}
        )
        AND grouptag.groupSeq = grp.groupSeq
      )
    )
GROUP BY 
  grp.groupSeq

