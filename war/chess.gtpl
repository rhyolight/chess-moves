<html>
    <head>
        <title>Chess Moves</title>
        
        <link rel="shortcut icon" href="/images/gaelyk-small-favicon.png" type="image/png">
        <link rel="icon" href="/images/gaelyk-small-favicon.png" type="image/png">
        
        <link rel="stylesheet" type="text/css" href="/css/main.css"/>
        <script src="../js/jquery-1.3.2.js"></script>
        <script src="../js/jquery-ui-1.7.2.custom/js/jquery-ui-1.7.2.custom.min.js"></script>
        <script src="../js/chess.js"></script>
    </head>
    <body>
        <div>
            <img src="/images/gaelyk.png">
            <img src="/images/google-app-engine-groovy.png" align="right">            
        </div>
        <div>

            <h1>Chess Moves Highlighter</h1>

            <table id="chessBoard">
            <tbody>
            <% (8..1).eachWithIndex { y, yIndex -> %>
                <tr>
                    <td class="label">${y}</td>
                    <% ('A'..'H').eachWithIndex { x, xIndex -> 
                        def shade = (xIndex + yIndex) % 2 == 0 ? 'light' : 'dark'
                        %>
                        
                        <td id="${x}${y}" class="cell ${shade}">
                            <% 
                            def piece = request.getAttribute('board').getCell(xIndex, 7-yIndex).piece
                            if (piece) { %>
                                <img src="/images/pieces/${piece.side}_${piece.class.simpleName.toLowerCase()}.png"/>
                            <% } else { %>
                                &nbsp;                
                            <% } %>
                        </td>
                    <% } %>
                </tr>
            <% } %>
                <tr>
                    <td/>
                    <% ('A'..'H').eachWithIndex { x, xIndex -> %>
                        <td class="label">${x}</td>
                    <% } %>
                </tr>
            </tbody>
            </table>
            <a href="/chess.groovy?reset=true">Reset Board</a>
            <span class='allMoves'>black</span>
            <span class='allMoves'>white</span>
        </div>
    </body>
</html>