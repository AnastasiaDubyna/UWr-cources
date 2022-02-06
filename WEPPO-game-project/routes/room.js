const express = require('express');
const router = express.Router();
const controller = require("../controllers/roomController");


router.get("/exist/:roomNumber", controller.doesRoomExist);

router.post("/create", controller.createNewRoom);

router.get("/available", controller.availableRooms);

router.get("/:roomNumber", controller.roomController);

module.exports = router;