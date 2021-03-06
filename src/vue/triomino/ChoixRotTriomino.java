package vue.triomino;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import modele.triomino.PieceTriomino;

public class ChoixRotTriomino {
	
	private JFrame frame;
	private boolean clicked;

	private int x;
	private int y;
	private int z;
	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public ChoixRotTriomino(PieceTriomino piece) {
		x=piece.getX();
		y=piece.getY();
		z=piece.getZ();
		initialize(piece);
	}

	
	private void initialize(PieceTriomino piece) {
		VuePieceTriomino label = new VuePieceTriomino(new PieceTriomino(x, y, z),29,64,1);
		VuePieceTriomino label1 = new VuePieceTriomino(new PieceTriomino(z, x, y),124, 64,1);
		VuePieceTriomino label2 = new VuePieceTriomino(new PieceTriomino(y, z, x),226, 64,1);
		
		
		VuePieceTriomino label3 = new VuePieceTriomino(new PieceTriomino(x, y, z),29,150,0);
		VuePieceTriomino label4 = new VuePieceTriomino(new PieceTriomino(z, x, y),124, 150,0);
		VuePieceTriomino label5 = new VuePieceTriomino(new PieceTriomino(y, z, x),226, 150,0);
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 328, 338);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel lblVeuillezChoisirLorientation = new JLabel("Veuillez choisir l'orientation de la piece ");
		lblVeuillezChoisirLorientation.setBounds(19, 23, 325, 29);
		frame.getContentPane().add(lblVeuillezChoisirLorientation);

		label.setBorder(new LineBorder(Color.RED));
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label.setBorder(new LineBorder(Color.RED));
				label1.setBorder(null);
				label2.setBorder(null);
				label3.setBorder(null);
				label4.setBorder(null);
				label5.setBorder(null);
				
				piece.setX(x);
				piece.setY(y);
				piece.setZ(z);

			}
		});
		label1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label1.setBorder(new LineBorder(Color.RED));
				label.setBorder(null);
				label2.setBorder(null);
				label3.setBorder(null);
				label4.setBorder(null);
				label5.setBorder(null);
				piece.setX(z);
				piece.setY(x);
				piece.setZ(y);
				
			}
		});
		label2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label2.setBorder(new LineBorder(Color.RED));
				label1.setBorder(null);
				label.setBorder(null);
				label3.setBorder(null);
				label4.setBorder(null);
				label5.setBorder(null);
				piece.setX(y);
				piece.setY(z);
				piece.setZ(x);  

			}
		});
	
		
		label3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label3.setBorder(new LineBorder(Color.RED));
				label1.setBorder(null);
				label2.setBorder(null);
				label.setBorder(null);
				label4.setBorder(null);
				label5.setBorder(null);
				piece.setX(x);
				piece.setY(y);
				piece.setZ(z);

			}
		});
		label4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label4.setBorder(new LineBorder(Color.RED));
				label1.setBorder(null);
				label2.setBorder(null);
				label.setBorder(null);
				label3.setBorder(null);
				label5.setBorder(null);
				piece.setX(z);
				piece.setY(x);
				piece.setZ(y);

			}
		});
		label5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label5.setBorder(new LineBorder(Color.RED));
				label1.setBorder(null);
				label2.setBorder(null);
				label.setBorder(null);
				label4.setBorder(null);
				label3.setBorder(null);
				piece.setX(y);
				piece.setY(z);
				piece.setZ(x);

			}
		});
		label.setBounds(29, 64, 50, 50);
		label3.setBounds(29, 150, 50, 50);
		label4.setBounds(124, 150, 50, 50);
		label5.setBounds(226, 150, 50, 50);
		frame.getContentPane().add(label);
		frame.getContentPane().add(label3);
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clicked = true;
				frame.setVisible(false);
				setX(x);
				setY(y);
				setZ(z);

			}
		});
		btnValider.setBounds(101, 221, 117, 29);
		frame.getContentPane().add(btnValider);
		
		
		label1.setBounds(124, 64, 50, 50);
		frame.getContentPane().add(label1);
		
	
		label2.setBounds(226, 64, 50, 50);
		frame.getContentPane().add(label2);
		frame.getContentPane().add(label4);
		frame.getContentPane().add(label5);
		frame.setVisible(true);
	}

	
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
