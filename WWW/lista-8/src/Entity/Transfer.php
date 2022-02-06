<?php

namespace App\Entity;

use App\Repository\TransferRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=TransferRepository::class)
 * @ORM\Table(name="transfers")
 */
class Transfer
{
    /**
     * @ORM\Id()
     * @ORM\GeneratedValue()
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     * @Assert\Length(
     *      min = 26,
     *      max = 26,
     *      minMessage = "Your account number must be {{ limit }} characters long",
     *      maxMessage = "Your account number must be {{ limit }} characters long"
     * )
     * @Assert\Regex("/^[0-9]{2}12349876[0-9]{16}/")
     */
    private $accountNumber;

    /**
     * @ORM\Column(type="string")
     * @Assert\Length(
     *      min = 6,
     *      max = 6,
     *      minMessage = "Your postcode must be {{ limit }} characters long",
     *      maxMessage = "Your postcode number must be {{ limit }} characters long"
     * )
     * @Assert\Regex("/^[0-9]{2}-[0-9]{3}/")
     */
    private $postcode;

    /**
     * @ORM\Column(type="string")
     * @Assert\Length(
     *      max = 50,
     *      maxMessage = "Your first name cannot be longer than {{ limit }} characters"
     * )
     */
    private $firstName;

    /**
     * @ORM\Column(type="string")
     * @Assert\Length(
     *      max = 50,
     *      maxMessage = "Your last name cannot be longer than {{ limit }} characters"
     * )
     */
    private $lastName;

    /**
     * @ORM\Column(type="string")
     * @Assert\Length(
     *      max = 50,
     *      maxMessage = "Street name cannot be longer than {{ limit }} characters"
     * )
     */
    private $street;

    /**
     * @ORM\Column(type="string")
     * @Assert\Length(
     *      max = 50,
     *      maxMessage = "Town name cannot be longer than {{ limit }} characters"
     * )
     */
    private $town;

    /**
     * @ORM\Column(type="string")
     * @Assert\Length(
     *      max = 50,
     *      maxMessage = "Description cannot be longer than {{ limit }} characters"
     * )
     */
    private $description;


    /**
     * @ORM\Column(type="float")
     */
    private $amount;

    /**
     * @Assert\Date
     * @var string A "Y-m-d" formatted value
     * @ORM\Column
     */
    private $date;

    public function getId(): ?int
    {
        return $this->id;
    }
}
