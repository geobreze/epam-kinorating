var sliderElement = document.querySelector('.slider');
var resultElement = document.querySelector('.rating-value');

sliderElement.addEventListener('change', function () {
    resultElement.textContent = sliderElement.value;
});
