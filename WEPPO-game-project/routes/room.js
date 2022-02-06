const express = require('express');
const router = express.Router();
const controller = require("../controllers/roomController");

router.get("/:roomNumber", controller.roomController);

router.get("/exist/:roomNumber", controller.doesRoomExist);

router.post("/create", controller.createNewRoom);

module.exports = router;