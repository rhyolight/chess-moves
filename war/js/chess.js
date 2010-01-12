var CHESS = {
    dropMove: function($piece, $from, $to, msg) {
        $('td.cell').removeClass('highlight');
        if (msg.indexOf('SUCCESS') > -1) {
            $from.empty();
            $to.empty();
            $to.append($piece);
            $piece.attr('style', 'position: relative')
                  .addClass('ui-draggable')
                  .mouseover(this.chessImageOver)
                  .mouseout(this.dehighlightAll)
                  .draggable();
        } else {
            alert(msg);
        }
    },
    chessImageOver: function() {
        $.ajax({
            type: "POST",
            url: "/possibleMoves.groovy",
            data: "cellDescriptor=" + $(this).parent().attr('id'),
            success: function(msg){
                var moves = msg.split(',');
                for (var i in moves) {
                    $('#' + moves[i]).addClass('highlight');
                }
            }
        });
    },
    dehighlightAll: function() {
        $cells.removeClass('highlight');
    }
};

$(function() {
    $chessImages = $('.cell img');
    $cells = $('td.cell');
    
    $chessImages.mouseover(CHESS.chessImageOver);
    $chessImages.mouseout(CHESS.dehighlightAll);
    
    $chessImages.draggable();
    $cells.droppable({
        activate: function(evt, ui) {
            $chessImages.droppable('disable');
        },
        drop: function(evt, ui) {
            $pieceImg = $(ui.draggable);
            $cell = $(this);
            $previousCell = $pieceImg.parent();
            if ($cell.attr('id') != $previousCell.attr('id')) {
                $.ajax({
                    type: "POST",
                    url: "/makeMove.groovy",
                    data: "from=" + $previousCell.attr('id') + "&to=" + $cell.attr('id'),
                    success: function(msg) {
                        CHESS.dropMove($pieceImg, $previousCell, $cell, msg)
                    }, 
                    faiure: function(msg) {
                        alert('there was an unexpected error: ' + msg);
                    }
                });
            }
            $chessImages.droppable('enable');
        }
    });
    
    $('span.allMoves').mouseover(function() {
        var side = $(this).text();
        $.ajax({
            type: "POST",
            url: "/possibleMoves.groovy",
            data: "allMoves=" + side,
            success: function(msg){
                var moves = msg.split(',');
                for (var i in moves) {
                    $('#' + moves[i]).addClass('highlight');
                }
            }
        });
    }).mouseout(CHESS.dehighlightAll);
    
});