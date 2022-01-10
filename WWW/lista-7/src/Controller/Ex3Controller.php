<?php


namespace App\Controller;

use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;

class Ex3Controller extends AbstractController
{
    /**
     * @Route("/ex3/main", name="ex3-main-page")
     */
    public function renderMain(): Response
    {
        return $this->render("ex3-main.html.twig", [
            "title" => "The best bank in Poland"
        ]);    }

    /**
     * @Route("/ex3/main/transfers", name="ex3-transfers")
     */
    public function renderTransfers(): Response
    {
        return $this->forward("App\Controller\Ex3Controller::renderExternalTransfers");
    }

    /**
     * @Route("/ex3/main/tranfers/domestic", name="ex3-domestic-transfers")
     */
    public function renderDomesticTransfers(): Response
    {
        return $this->forward("App\Controller\Ex3Controller::renderExternalTransfers");
    }

    /**
     * @Route("/ex3/main/transfers/domestic/external", name="ex3-external-transfers")
     */
    public function renderExternalTransfers(): Response
    {
        return $this->render("ex3-main.html.twig", [
            "title" => "Transfer to external account"
        ]);
    }

    /**
     * @Route("/ex3/main/transfers/domestic/own", name="ex3-own-transfers")
     */
    public function renderOwnTransfers(): Response
    {
        return $this->render("ex3-main.html.twig", [
            "title" => "Transfer to own account"
        ]);
    }

    /**
     * @Route("/ex3/main/list/{date}/{amount}", name="ex3-transfers-list")
     * @param $date
     * @param $amount
     * @return Response
     */
    public function renderTransfersList($date, $amount): Response
    {
        return $this->render("ex3-additional.html.twig", [
            "title" => "List of transfers",
            "date" => $date,
            "amount" => $amount
        ]);
    }

    /**
     * @Route("/ex3/main/cards", name="ex3-cards")
     */
    public function renderCards(): Response
    {
        return $this->forward("App\Controller\Ex3Controller::renderCreditCards");
    }

    /**
     * @Route("/ex3/main/cards/credit", name="ex3-credit-cards")
     */
    public function renderCreditCards(): Response
    {
        return $this->render("ex3-main.html.twig", [
            "title" => "Credit Cards"
        ]);
    }

    /**
     * @Route("/ex3/main/cards/account", name="ex3-account-cards")
     */
    public function renderAccountCards(): Response
    {
        return $this->render("ex3-main.html.twig", [
            "title" => "Account Cards"
        ]);
    }


}