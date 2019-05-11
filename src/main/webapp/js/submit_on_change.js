var selectedItems = document.querySelectorAll('.submit-on-change');
selectedItems.forEach(function (item) {
    item.addEventListener('change', function () {
        item.form.submit();
    });
});