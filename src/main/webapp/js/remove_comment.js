function sendRequest(contextUrl, commentId) {
    var httpRequest = new XMLHttpRequest();
    httpRequest.open('POST', contextUrl + '/?command=delete_comment&id=' + commentId, true);
    httpRequest.send();
    return httpRequest;
}

function removeButtonEventListener(button, contextUrl) {
    var commentId = button.getAttribute('data-id');
    var httpRequest = sendRequest(contextUrl, commentId);
    httpRequest.onreadystatechange = function () {
        if (httpRequest.readyState === XMLHttpRequest.DONE && httpRequest.status === 200) {
            document.getElementById("comment" + commentId).remove();
        }
    };
}

function addButtonListeners(contextUrl) {
    var deleteButtons = document.querySelectorAll('.delete-comment');
    deleteButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            removeButtonEventListener(button, contextUrl);
        });
    });
}