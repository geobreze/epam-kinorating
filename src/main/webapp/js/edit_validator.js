function addFilmEditValidationListener(emptyMessage, tooLongMessage) {
    var form = document.getElementById("edit-form");
    form.addEventListener("submit", function (ev) {
        var titleInput = document.getElementById("title-input");
        var descriptionArea = document.getElementById("description-area");
        var errorBlockId = "film-edit-error";
        if (isInputEmpty(titleInput) || isInputEmpty(descriptionArea)) {
            printErrorMessage(emptyMessage, errorBlockId);
            ev.preventDefault();
        }
        if (isInputTooLong(titleInput)) {
            printErrorMessage(tooLongMessage, errorBlockId);
            ev.preventDefault();
        }
    });
}

function addCommentValidationListener(emptyMessage) {
    var form = document.getElementById("comment-form");
    form.addEventListener("submit", function (ev) {
        var textInput = document.getElementById("comment-text-input");
        var errorBlockId = "new-comment-error";
        if (isInputEmpty(textInput)) {
            printErrorMessage(emptyMessage, errorBlockId);
            ev.preventDefault();
        }
    });
}

function printErrorMessage(message, blockId) {
    var errorBlock = document.getElementById(blockId);
    errorBlock.innerHTML = message;
}

function isInputTooLong(input) {
    var MAX_LENGTH = 255;
    return input.value.length > MAX_LENGTH;
}

function isInputEmpty(input) {
    return isEmpty(input.value);
}

function isEmpty(str) {
    return (!str || 0 === str.length);
}