package control;

import java.util.List;

public class Valuation {

	private Minimax mm;

	public Valuation(Minimax minimax) {
		mm = minimax;
	}

	public double getValue(int[] board, int depth) {
		double value = 0;

		for (int i = 11; i <= 88; i++) {
			if (board[i] != Minimax.POS_FREE && board[i] != Minimax.ERROR) {
				value += getFigValue(i, board);
			}
		}

		return value;
	}

	public double getFigValue(int figPos, int[] board) {
		double value = 0;

		List<Integer> targets = mm.getAllMoves(board, figPos);

		for (int target : targets) {
			value += getTargetValue(target, mm.getPlayerByFig(board[figPos]));
		}

		return value / 9;
	}

	public double getTargetValue(int target, int player) {
		if (player == Minimax.PLAYER_WHITE)
			return (7 - (target / 10 - 1)) / 7.;
		else
			return (-(target / 10 - 1)) / 7.;
	}

}
