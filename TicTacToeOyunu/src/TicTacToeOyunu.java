import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeOyunu extends JFrame {

    private JButton[][] buttons;
    private char currentPlayer;

    public TicTacToeOyunu() {
        setTitle("Tic Tac Toe Oyunu");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        currentPlayer = 'X';

        // JButton bileşenlerini oluştur ve ActionListener ekleyerek tıklama olaylarını dinle
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].addActionListener(new ButtonClickListener());
                add(buttons[i][j]);
            }
        }

        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();

            // Eğer buton zaten kullanıldıysa veya oyun bitti ise tıklamayı işleme alma
            if (clickedButton.getText().equals("") && !oyunBittiMi()) {
                clickedButton.setText(Character.toString(currentPlayer));
                if (oyunBittiMi()) {
                    JOptionPane.showMessageDialog(null, "Oyun bitti! Kazanan: " + currentPlayer);
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
        }
    }

    private boolean oyunBittiMi() {
        // Satırları, sütunları ve çaprazları kontrol et
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(Character.toString(currentPlayer)) &&
                    buttons[i][1].getText().equals(Character.toString(currentPlayer)) &&
                    buttons[i][2].getText().equals(Character.toString(currentPlayer))) {
                return true; // Satır kontrolü
            }
            if (buttons[0][i].getText().equals(Character.toString(currentPlayer)) &&
                    buttons[1][i].getText().equals(Character.toString(currentPlayer)) &&
                    buttons[2][i].getText().equals(Character.toString(currentPlayer))) {
                return true; // Sütun kontrolü
            }
        }

        if (buttons[0][0].getText().equals(Character.toString(currentPlayer)) &&
                buttons[1][1].getText().equals(Character.toString(currentPlayer)) &&
                buttons[2][2].getText().equals(Character.toString(currentPlayer))) {
            return true; // Sol üstten sağ alta çapraz kontrolü
        }

        if (buttons[0][2].getText().equals(Character.toString(currentPlayer)) &&
                buttons[1][1].getText().equals(Character.toString(currentPlayer)) &&
                buttons[2][0].getText().equals(Character.toString(currentPlayer))) {
            return true; // Sağ üstten sol alta çapraz kontrolü
        }

        return false; // Hiçbir kazanan durumu yok
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeOyunu());
    }
}
