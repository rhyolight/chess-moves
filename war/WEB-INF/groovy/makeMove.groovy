import net.dangertree.chess.Board

if (!session) {
  session = request.getSession(true);
}

try {
    session.board.move(params.from, params.to)
    println 'SUCCESS'
} catch (Exception e) {
    println "FAILED: $e"
}