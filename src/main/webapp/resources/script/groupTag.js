/**
 * 
 */
 
 
function getTags(groupSeq) {
	let result;
    $.ajax({
        type: 'get',
		async: false,
        dataType: 'json',
        url: `/groupTag/${groupSeq}`,
        success: function (data) {
			console.log(data);
			result = data;
			$("#tags").empty();
			result.forEach(function(data, idx) {
				let element = `<div class="tag" data-groupTagSeq="${data['groupTagSeq']}">`;
				element += data['groupTagName'];
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

function createTag(groupSeq, groupTagName) {
	let result;
    $.ajax({
        type: 'post',
		async: false,
        dataType: 'json',
        url: '/groupTag/create',
        data: {
        	"groupSeq": groupSeq,
        	"groupTagName": groupTagName
        },
        success: function (data) {
			result = data;
			getTags(groupSeq);
        },
        error: function (request, status, error) {
            console.log('code: '+request.status+"\n"+'message: '+request.responseText+"\n"+'error: '+error +"\n"+'status: '+status);
        }
    });
	return result;
};

function deleteTag(groupSeq, groupTagSeq) {
	let result;
    $.ajax({
        type: 'post',
		async: false,
        dataType: 'json',
        url: `/groupTag/delete/${groupTagSeq}`,
        success: function (data) {
			result = data;
			getTags(groupSeq);
        },
        error: function (request, status, error) {
            console.log('code: '+request.status+"\n"+'message: '+request.responseText+"\n"+'error: '+error +"\n"+'status: '+status);
        }
    });
	return result;
};