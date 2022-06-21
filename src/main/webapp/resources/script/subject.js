/**
 * 
 */
 
 
function getSubjectList(boardSeq) {
	let result;
    $.ajax({
        type: 'get',
		async: false,
        dataType: 'json',
        url: `/admin/subject`,
        data: {
        	"boardSeq" : boardSeq
        },
        success: function (data) {
			result = data;
        },
        error: function (request, status, error) {
            console.log('code: '+request.status+"\n"+'message: '+request.responseText+"\n"+'error: '+error +"\n"+'status: '+status);
        }
    });
	return result;
};
