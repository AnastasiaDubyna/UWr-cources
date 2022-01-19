<?php

function findByTitlePart($titlePart) {
    $jsonItem = file_get_contents("moviedata.json");
    $objItems = json_decode($jsonItem);
    $foundTitles = [];
    foreach ($objItems as $movie) {
        if (strpos($movie->title, $titlePart)) {
            array_push($foundTitles, $movie->title);
            if (count($foundTitles) == 10) {
                break;
            }
        }
    }

    return $foundTitles;
}


if (!empty($_GET["inputData"])) {
    $foundTitles = findByTitlePart($_GET["inputData"]);
    echo json_encode($foundTitles);
}



