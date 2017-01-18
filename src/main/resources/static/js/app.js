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
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/greetings', function (greeting) {
            var model = JSON.parse(greeting.body);
            if(model.name === "start") {
                board.start();
            }
            board.move(model.name);
        });
    });
}


function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

$( window ).unload(function() {
    disconnect();
});

$(function () {
    board = ChessBoard('board', cfg);
    connect();
    $('#flipOrientationBtn').on('click', board.flip);
});