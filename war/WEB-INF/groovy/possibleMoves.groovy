import net.dangertree.chess.Board

if (!session) {
  session = request.getSession(true);
}

if (params.cellDescriptor) {
    def piece = session.board.getPiece(params.cellDescriptor)
    println piece.possibleMoves.collect { it.descriptor }.join(',')    
} else if (params.allMoves) {
    println session.board.getAllPossibleMoves(params.allMoves).collect { it.descriptor }.join(',')
}
