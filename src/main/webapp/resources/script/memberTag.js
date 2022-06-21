/**
 * 
 */
 
 
function getTags(memberSeq) {
	let result;
    $.ajax({
        type: 'get',
		async: false,
        dataType: 'json',
        url: `/memberTag/${memberSeq}`,
        success: function (data) {
			console.log(data);
			result = data;
			$("#tags").empty();
			result.forEach(function(data, idx) {
				let element = `<div class="tag" data-memberTagSeq="${data['memberTagSeq']}">`;
				element += data['memberTagName'];
				element += `<button type="button" class="deleteBtn">x</button>`;
				element += `</div>`;
				$("#tags").append(element);
			});
        },
        error: function (request, status, error) {
            console.log('code: '+request.status+"\n"+'message: '+request.responseText+"\n"+'error: '+error +"\n"+'status: '+status);
        }
    });
	return result;
};

function createTag(memberSeq, memberTagName) {
	let result;
    $.ajax({
        type: 'post',
		async: false,
        dataType: 'json',
        url: '/memberTag/create',
        data: {
        	"memberSeq": memberSeq,
        	"memberTagName": memberTagName
        },
        success: function (data) {
			result = data;
			getTags(memberSeq);
        },
        error: function (request, status, error) {
            console.log('code: '+request.status+"\n"+'message: '+request.responseText+"\n"+'error: '+error +"\n"+'status: '+status);
        }
    });
	return result;
};

function deleteTag(memberSeq, memberTagSeq) {
	let result;
    $.ajax({
        type: 'post',
		async: false,
        dataType: 'json',
        url: `/memberTag/delete/${memberTagSeq}`,
        success: function (data) {
			result = data;
			getTags(memberSeq);
        },
        error: function (request, status, error) {
            console.log('code: '+request.status+"\n"+'message: '+request.responseText+"\n"+'error: '+error +"\n"+'status: '+status);
        }
    });
	return result;
};