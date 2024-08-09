import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int bardHeight = 700; //extra 50 height for text label

    JFrame frame = new JFrame("Tic-Tac-Toe");

    JLabel textLabel = new JLabel(); //for heading
    JPanel textPanel = new JPanel(); //text for heading
    JPanel boardPanel = new JPanel(); //game board
    JLabel statsText = new JLabel();
    JPanel statsPanel = new JPanel();

    //butthons for each cell of board
    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    JButton xScore = new JButton();
    JButton restart = new JButton();
    JButton yScore = new JButton();

    int x = 0;
    int y = 0;

    //vars to track game result
    int turns = 0;
    boolean gameOver = false;

    TicTacToe() {
        //frame or game window
        frame.setVisible(true);
        frame.setSize(boardWidth, bardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //H1
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        //H1 Text
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        //game board Panel
        boardPanel.setLayout(new GridLayout(4, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);
       
        
        //intialising each button/tile       
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tileinit(tile, "");

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) {
                            ResetGame();

                            return;
                        }

                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();

                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                System.out.println(currentPlayer + "'s turn --" + gameOver);
                                textLabel.setText(currentPlayer + "'s turn.");
                            }

                        }
                    }
                });
            }
        }

        
        String xno = "X: " + x;
        tileinit(xScore, xno);

        
        tileinit(restart, "Restart");
        restart.setFont(new Font("Arial", Font.BOLD, 35));;

        String yno = "Y: " + y;
        tileinit(yScore,yno);

        boardPanel.add(xScore);
        boardPanel.add(restart);
        boardPanel.add(yScore);

        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                x = 0;
                y = 0;
                xScore.setText("X: " + x);
                yScore.setText("Y: "+y);
               ResetGame();
            }
        });
        
    }

    void checkWinner() {
        //check horizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() != "") {
                if (board[r][0].getText() == board[r][1].getText() && board[r][1].getText() == board[r][2].getText()) {
                    setWinnerTitle();
                    for (int c = 0; c < 3; c++) {
                        setWinnerTile(board[r][c]);
                    }
                }
            }    
        }
        //check Vertical
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() != "") {
                if (board[0][c].getText() == board[1][c].getText() && board[1][c].getText() == board[2][c].getText()) {
                    setWinnerTitle();
                    for (int r = 0; r < 3; r++) {
                        setWinnerTile(board[r][c]);
                    }
                    return;
                }
            }    
        }
        
        //check diagonal
        if (board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText()
                && board[0][0].getText() != "") {
            setWinnerTitle();
            for (int i = 0; i < 3; i++) {
                setWinnerTile(board[i][i]);
            }
            return;
        }
        //check Anti Diagonal

        if (board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText()
                && board[1][1].getText() != "") {
            setWinnerTitle();
            setWinnerTile(board[0][2]);
            setWinnerTile(board[1][1]);
            setWinnerTile(board[2][0]);
            return;
        }

        if (turns == 9) {
            setTie();
            gameOver = true;
            return;
        }
        return;
    }

    void setWinnerTitle() {
        textLabel.setText("Winner is " + currentPlayer);
        if (currentPlayer == playerX) {
            x++;
            xScore.setText("X: "+x);
        }
        if (currentPlayer == playerO) {
            y++;
            yScore.setText("Y: "+y);
        }
        gameOver = true;
        return;
    }

    void setWinnerTile(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
    }

    void setTie() {
        textLabel.setText("Tie!");
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setForeground(Color.orange);

            }
        }
        return;
    }

    void ResetGame() {

        gameOver = false;
        textLabel.setText("Tic-Tac-Toe");
        turns = 0;
        currentPlayer = playerX;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");
                board[r][c].setForeground(Color.white);
                board[r][c].setBackground(Color.darkGray);

            }
        }
        return;
    }

    void tileinit(JButton tile, String text) {
        tile.setBackground(Color.darkGray);
        tile.setForeground(Color.white);
        tile.setFont(new Font("Arial", Font.BOLD, 50));
        tile.setFocusable(false);
        tile.setText(text);
    }

   

}
