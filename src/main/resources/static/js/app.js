var stompClient = null;
var board;
var cfg = {
    draggable: false,
    position: 'start',
    sparePieces: false
};
function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/topic/moves', function (moves) {
            var model = JSON.parse(moves.body);
            if (model.name === "start") {
                board.start();
            }
            board.move(model.name);
        });
        stompClient.subscribe('/topic/messages', function (moves) {
            var model = JSON.parse(moves.body);

            showMessage(model.message);
        });
    });
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}
function sendMessage() {
    stompClient.send("/app/message", {}, JSON.stringify({'message': $("#message").val()}));
    $("#message").val('');
}

$(window).unload(function () {
    disconnect();
});

$(function () {
    board = ChessBoard('board', cfg);
    connect();
    $('#flipOrientationBtn').on('click', board.flip);
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#send").click(function () {
        sendMessage();
    });
});