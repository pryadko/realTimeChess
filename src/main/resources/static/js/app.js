var stompClient = null;
var board;
var hod = '';
var cfg = {
    draggable: true,
    position: 'start',
    onDrop: function (source, target, piece, newPos, oldPos, orientation) {
        hod += '"'+source + '-'+target + '", ' ;
        console.log(hod);
    },
    sparePieces: false
};
function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            var name = JSON.parse(greeting.body).name;
            if(name === "start") {
                board.start();
            }
            board.move(name);
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