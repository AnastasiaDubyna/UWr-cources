<?php


namespace App\Controller;

use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Response;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;


class MainController extends AbstractController {
    /**
     * @Route("/")
     */

    public function index() {
        return $this->render("ex2.html.twig");
    }

}