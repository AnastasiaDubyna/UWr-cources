<?php


namespace App\Controller;

use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;


class Ex2Controller extends AbstractController
{
    /**
     * @Route("/ex2")
     */

    public function index()
    {
        $table = array(
            array(
                "position" => 1,
                "score" => 17,
                "name" => "Mary",
                "surname" => "Kiramman",
                "town" => "Alston",
                "team" => "Alston Team"
            ),
            array(
                "position" => 2,
                "score" => 15,
                "name" => "Violet",
                "surname" => "Freeman",
                "town" => "Birmingham",
                "team" => "Birmingham Team"
            ),
            array(
                "position" => 3,
                "score" => 12,
                "name" => "John",
                "surname" => "Hollow",
                "town" => "Alston",
                "team" => "Alston Team"
            ),
            array(
                "position" => 4,
                "score" => 11,
                "name" => "Jane",
                "surname" => "Doe",
                "town" => "Manchester",
                "team" => "Manchester Team"
            ),
            array(
                "position" => 5,
                "score" => 10,
                "name" => "Ivan",
                "surname" => "Mace",
                "town" => "Cardiff",
                "team" => "Cardiff Team"
            ),
            array(
                "position" => 6,
                "score" => 8,
                "name" => "Jack",
                "surname" => "Scotch",
                "town" => "Nottingham",
                "team" => "Nottingham Team"
            ),
            array(
                "position" => 7,
                "score" => 7,
                "name" => "Samwel",
                "surname" => "Tarly",
                "town" => "Derby",
                "team" => "Derby Team"
            ),
            array(
                "position" => 8,
                "score" => 5,
                "name" => "Martin",
                "surname" => "Black",
                "town" => "Aberdeen",
                "team" => "Aberdeen Team"
            ),
            array(
                "position" => 9,
                "score" => 3,
                "name" => "Joe",
                "surname" => "West",
                "town" => "Peterborough",
                "team" => "Peterborough Team"
            ),
            array(
                "position" => 10,
                "score" => 2,
                "name" => "Sara",
                "surname" => "Trust",
                "town" => "Oxford",
                "team" => "Oxford Team"
            ),
            array(
                "position" => 11,
                "score" => 0,
                "name" => "Tatia",
                "surname" => "Petrova",
                "town" => "Lancaster",
                "team" => "Lancaster Team"
            ),
            array(
                "position" => 12,
                "score" => 0,
                "name" => "Jane",
                "surname" => "Smith",
                "town" => "Norwich",
                "team" => "Norwich Team"
            )

        );
        return $this->render("ex2.html.twig", [
            "table" => $table
    ]);
    }
}
