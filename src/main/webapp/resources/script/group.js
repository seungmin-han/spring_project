/**
 * 
 */
 
 
function joinGroup(groupSeq) {
	let result;
    $.ajax({
        type: 'post',
		async: false,
        dataType: 'json',
        url: `/group/join`,
        data: {
        	"groupSeq" : groupSeq
        },
        success: function (data) {
			location.href="/group/search";
        },
        error: function (request, status, error) {
            console.log('code: '+request.status+"\n"+'message: '+request.responseText+"\n"+'error: '+error +"\n"+'status: '+status);
        }
    });
	return result;
};

function kickMember(joinStatusSeq, groupSeq) {
	let result;
    $.ajax({
        type: 'post',
		async: false,
        dataType: 'json',
        url: '/group/kick',
        data: {
        	"joinStatusSeq" : joinStatusSeq
        },
        success: function (data) {
			location.href=`/group/update/${groupSeq}`;
        },
        error: function (request, status, error) {
            console.log('code: '+request.status+"\n"+'message: '+request.responseText+"\n"+'error: '+error +"\n"+'status: '+status);
        }
    });
	return result;
};

function acceptMember(joinStatusSeq, groupSeq) {
	let result;
    $.ajax({
        type: 'post',
		async: false,
        dataType: 'json',
        url: '/group/accept',
        data: {
        	"joinStatusSeq" : joinStatusSeq
        },
        success: function (data) {
			location.href=`/group/update/${groupSeq}`;
        },
        error: function (request, status, error) {
            console.log('code: '+request.status+"\n"+'message: '+request.responseText+"\n"+'error: '+error +"\n"+'status: '+status);
        }
    });
	return result;
};

function changeMaster(memberSeq, groupSeq) {
	let result;
    $.ajax({
        type: 'post',
		async: false,
        dataType: 'json',
        url: '/group/changeMaster',
        data: {
        	"memberSeq" : memberSeq
        	, "groupSeq" : groupSeq
        },
        success: function (data) {
			location.href=`/group/${groupSeq}`;
        },
        error: function (request, status, error) {
            console.log('code: '+request.status+"\n"+'message: '+request.responseText+"\n"+'error: '+error +"\n"+'status: '+status);
        }
    });
	return result;
};