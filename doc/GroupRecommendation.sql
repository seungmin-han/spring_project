select
	/*
    tbl_group (grp)
 groupSeq: 그룹의 PK
 groupName: 이름
 gorupText: 소개
 grp.memberSeq: 그룹장PK
*/
	grp.groupSeq 
  ,
	groupName 
  ,
	groupText
  ,
	grp.memberSeq 
/*
    joinStatus (jst)
 joinStatusCdText: 가입 상태 코드에 따른 텍스트
 joinStatusCd: 가입 상태 코드 (Y:가입됨, W:대기중, N:미가입)
 memberCount: 해당 그룹의 가입된 사용자들의 수(서브쿼리)
*/
	,
	case
		when joinStatusCd = 'Y' 
  	then '가입됨'
		when joinStatusCd = 'W' 
  	then '승인대기'
		else null
	end as joinStatusCdText 
  ,
	joinStatusCd 
  ,
	(
	select
		COUNT(*)
	from
		joinStatus
	where
		groupSeq = grp.groupSeq
		and joinStatusCd = 'Y'
	group by
		groupSeq
  ) as memberCount 
/*
    groupTag (gt)
 groupTags: 해당 그룹의 태그들을 ','로 묶은 텍스트
*/
	,
	GROUP_CONCAT(distinct groupTagName separator ',') as groupTags
/*
    groupImage (gi)
 groupImageName: 해당 그룹의 이미지 경로
*/
	,
	groupImageName
from
	tbl_group as grp 
/*
    그룹 테이블과 가입 상태 테이블 LEFT JOIN (로그인한 사용자의 가입 상태 데이터)
*/
left join joinStatus as jst on
	grp.groupSeq = jst.groupSeq
	and jst.memberSeq =
	#{memberSeq}
	and jst.joinStatusDelNy = 0
/*
    그룹 테이블과 그룹 이미지 테이블 LEFT JOIN (그룹에서 현재 사용중인 이미지)
*/
left join groupImage as gi on
	gi.groupSeq = grp.groupSeq
	and gi.groupImageThumNy = 1
	and gi.groupImageDelNy = 0
/*
    그룹 테이블과 그룹 태그 테이블 LEFT JOIN (해당 그룹 태그)
*/
left join groupTag as gt on
	gt.groupSeq = grp.groupSeq
where
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
	and not exists
  (
	select
		memberSeq
	from
		joinStatus
	where
		groupSeq = grp.groupSeq
		and not joinStatusCd = 'N'
		and memberSeq =
		#{memberSeq}
  ) 
/*
    조건 3. (AND)

    아래 조건 중 하나만 성립되어도 참(True)
*/
	and 
  (
/*
    조건 3-1. (OR)

    해당 그룹의 태그 중 내가 설정한 태그와
    중복되는 데이터가 존재해야 함.

    ex) 내가 설정한 태그: 축구
        그룹에서 설정한 태그: 축구
        인 경우 True
*/
	exists 
    (
	select
		memberTagName
	from
		memberTag
	where
		memberSeq =
		#{memberSeq}
		and memberTagName in 
        (
		select
			groupTagName
		from
			groupTag
		where
			groupTag.groupSeq = grp.groupSeq
  	  )  	
	  )
	or 
/*
    조건 3-2. (OR)

    해당 그룹의 태그 중 내가 설정한 태그(텍스트)가
    포함되는 데이터가 존재해야 함. 
    
    ex) 내가 설정한 태그: 축구
        그룹에서 설정한 태그: 축구관람
        인 경우 True
*/
	exists 
    (
	select
		groupTagName
	from
		groupTag
	where
		groupTagName regexp 
        (
		select
			GROUP_CONCAT(memberTagName separator '|') as memberTagNames
		from
			memberTag
		where
			memberSeq =
			#{memberSeq}
        )
		and grouptag.groupSeq = grp.groupSeq
    )
  )
group by
	grp.groupSeq


/*===================================================================*/
select
	grp.groupSeq 
  ,
	groupName 
  ,
	groupText
  ,
	grp.memberSeq 
  ,
	case
		when joinStatusCd = 'Y' 
  	then '가입됨'
		when joinStatusCd = 'W' 
  	then '승인대기'
		else null
	end as joinStatusCdText 
  ,
	joinStatusCd 
  ,
	(
	select
		COUNT(*)
	from
		joinStatus
	where
		groupSeq = grp.groupSeq
		and joinStatusCd = 'Y'
	group by
		groupSeq
  ) as memberCount 
  ,
	GROUP_CONCAT(distinct groupTagName separator ',') as groupTags
  ,
	groupImageName
from
	tbl_group as grp
left join joinStatus as jst on
	grp.groupSeq = jst.groupSeq
	and jst.memberSeq = 9
left join groupImage as gi on
	gi.groupSeq = grp.groupSeq
	and gi.groupImageThumNy = 1
left join groupTag as gt on
	gt.groupSeq = grp.groupSeq
where
	grp.groupDelNy = 0
	and not exists(
	select
		memberSeq
	from
		joinStatus
	where
		groupSeq = grp.groupSeq
		and not joinStatusCd = 'N'
		and memberSeq = 9
    )
	and (
      exists (
	select
		memberTagName
	from
		memberTag
	where
		memberSeq = 9
		and memberTagName in (
		select
			groupTagName
		from
			groupTag
		where
			groupTag.groupSeq = grp.groupSeq
  		  )  	
	  )
	or (
		exists (
	select
		groupTagName
	from
		groupTag
	where
		groupTagName regexp (
		select
			GROUP_CONCAT(memberTagName separator '|') as memberTagNames
		from
			memberTag
		where
			memberSeq = 9
            )
		and grouptag.groupSeq = grp.groupSeq
        )
      )
    )
group by
	grp.groupSeq