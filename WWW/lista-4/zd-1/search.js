$(document).ready(function(){

    function search(searchedStr) {
        $(`li:contains(${searchedStr})`).each(function() {
            console.log($(this));
            highlight($(this), $("#search-input").val());
        });
    }

    function highlight($listItem, searchedStr) {
        const listItemValue = $listItem.text();
        const startIndex = listItemValue.indexOf(searchedStr);
        const endIndex = startIndex + searchedStr.length;
        $listItem.html(`${listItemValue.substring(0, startIndex)}<span class='highlighted'>${searchedStr}</span>${listItemValue.substring(endIndex, listItemValue.length)}`);
    }

    function removeHighlight() {
        $('span').contents().unwrap();
    }

    $("#search-input").keyup(function(event) {
        removeHighlight();
        const inputValue = event.target.value;
        if (inputValue.length >= 3) {
            search(inputValue);
        }

    })

  
  });