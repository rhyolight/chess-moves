import net.dangertree.chess.Board
import net.dangertree.chess.pieces.Queen

if (!session) {
  session = request.getSession(true);
}

if (params.reset) {
    session.board = null
}

if (!session.board) {
    session.board = new Board(true)
}

request.setAttribute 'board', session.board

forward '/chess.gtpl'