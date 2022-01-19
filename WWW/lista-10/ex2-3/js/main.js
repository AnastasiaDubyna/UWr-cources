$(document).ready(function () {
    const debounce = (func, wait) => {
        let timeout;

        return function executedFunction(...args) {
            const later = () => {
                clearTimeout(timeout);
                func(...args);
            };

            clearTimeout(timeout);
            timeout = setTimeout(later, wait);
        };
    };

    const throttle = (func, wait) => {
        let timeout;

        return function executedFunction(...args) {
            if (timeout) {
                return;
            }
            timeout  =  setTimeout(function () {
                func(...args);
                timeout  =  undefined;
            }, wait);
        };
    };

    const keyUpListener = event => {
        const inputData = event.target.value;
        $.ajax({
            type: "GET",
            url: "process.php",
            data: {"inputData": inputData},
            dataType: "json",
            encode: true
        })
            .done(data => {
                $(".suggestions-container").empty();
                $.each(data, (index, value) => {
                    $(".suggestions-container").append(`<p>${value}</p>`);
                })
            })
            .fail(() => {
                $(".suggestions-container").empty();
            });
    };

    const keyUpListenerWithDebounce = debounce(keyUpListener, 200);
    const keyUpListenerWithThrottling = throttle(keyUpListener, 200);
    $("input").keyup(keyUpListenerWithThrottling);
});