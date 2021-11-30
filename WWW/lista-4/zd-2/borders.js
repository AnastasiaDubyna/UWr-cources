$(document).ready(function(){

    function toggleDataMarked(img) {
        if (img.data("marked") == "true") {
            img.data("marked", "false");
        } else {
            img.data("marked", "true");
        }
    }

    function toggleSelection(event) {
        const selectedImg = $(event.target);
        selectedImg.toggleClass("selected");
        toggleDataMarked(selectedImg);

        console.log($(event.target).data("marked"));
    }

    function toggleSelectionAll(event) {
        $("img").each(function() {
            $(this).toggleClass("selected");
            toggleDataMarked($(this));
        })
        if ($("img").first().data("marked") == "true") {
            $(event.target).html("Unmark All")
        } else {
            $(event.target).html("Mark All")
        }
    }





    $("img").click(toggleSelection);
    $("#mark-all-btn").click(toggleSelectionAll);

})