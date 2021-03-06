package modele.triomino;

import java.util.ArrayList;
import java.util.Collections;

import modele.Joueur;
import modele.Point;

public class JoueurTriomino extends Joueur{
	
	private ArrayList<PieceTriomino> main;
	private int nombreDePioches;

	public int getNombreDePioches() {
		return nombreDePioches;
	}

	public void setNombreDePioches(int nombreDePioches) {
		this.nombreDePioches = nombreDePioches;
	}

	

	public ArrayList<PieceTriomino> getMain() {
		return main;
	}

	public void setMain(ArrayList<PieceTriomino> main) {
		this.main = main;
	}

	public JoueurTriomino(int jeu, String nom, boolean cpu) {
		super(nom,0,cpu);
	
	
			this.main = new ArrayList<PieceTriomino>();
	}

	public ArrayList<PieceTriomino> getAllPiecesSwipes() {
		ArrayList<PieceTriomino> resultat = new ArrayList<PieceTriomino>();
		for (PieceTriomino p : main) {
			resultat.addAll(p.getAllSwipes());
		}
		return resultat;
	}

	public void initMain(ArrayList<PieceTriomino> pieces, int tailleMain) { // initialiser
		// la main ;
		// tailleMain
		// va varier
		// selon le
		// jeu
		for (int i = 0; i < tailleMain; i++) {
			Collections.shuffle(pieces);
			this.main.add(pieces.get(0));
			pieces.remove(0);
		}
	}

	public boolean piocher(ArrayList<PieceTriomino> pieces, int jeu) { // jeu=0
																		// domino
																		// ,
		// jeu=1
		// triomino
		if (pieces.size() == 0) {
			return false;
		}

		Collections.shuffle(pieces);
		this.main.add(pieces.get(0));
		pieces.remove(0);

		return true;

	}

	public boolean mainVide() {
		return (this.main.size() == 0);
	}

	public ArrayList<Point> ajouterExtremites(int jeu, ModeleTriomino table, int newX, int newY) {

		ArrayList<Point> resultat = new ArrayList<Point>();
		int x = newX;
		int y = newY;
		int direction = ModeleTriomino.getDirection(x, y);

		if (table.getTable()[x][y + 1] == null) {

			table.getExtremite().add(new Point(x, y + 1));
			resultat.add(new Point(x, y + 1));
		}
		if (table.getTable()[x][y - 1] == null) {

			table.getExtremite().add(new Point(x, y - 1));
			resultat.add(new Point(x, y - 1));
		}

		if (table.getTable()[x + 1][y] == null && direction == 1) {

			table.getExtremite().add(new Point(x + 1, y));
			resultat.add(new Point(x + 1, y));
		}

		if (table.getTable()[x - 1][y] == null && direction == 0) {
			table.getExtremite().add(new Point(x - 1, y));
			resultat.add(new Point(x - 1, y));
		}

		return resultat;
	}

	public void supprimerExtremites(int jeu, ModeleTriomino table, int newX, int newY) {
		table.getExtremite().remove(new Point(newX, newY));
	}

	public Point coup(int jeu, PieceTriomino piece, int indexDePieceDansLaMain, ModeleTriomino table, int posX,
			int posY) {
		if (!this.isCpu()) { // humain
			if (table.coupValide(jeu, piece, new Point(posX, posY))) {

				table.getTable()[posX][posY] = piece;

				this.supprimerExtremites(jeu, table, posX, posY);
			 this.ajouterExtremites(jeu, table, posX, posY);

				this.setScore(this.getScore()+this.getMain().get(indexDePieceDansLaMain).valeur())  ;
				System.out.println("ajout score humain + " + this.getMain().get(indexDePieceDansLaMain).valeur());
				this.getMain().remove(indexDePieceDansLaMain);
				if (table.verifTripleHexagone(new Point(posX, posY))) {
					this.setScore(this.getScore()+70);
					System.out.println("joueur "+this.getNom()+" a fait un triple hexagone");
				} else if (table.verifDoubleHexagone(new Point(posX, posY))) {
					this.setScore(this.getScore()+60);
					System.out.println("joueur "+this.getNom()+" a fait un double hexagone");
				} else if (table.verifHexagone(new Point(posX, posY), 0)
						|| table.verifHexagone(new Point(posX, posY), 1)
						|| table.verifHexagone(new Point(posX, posY), 2)) {
					this.setScore(this.getScore()+50);
					System.out.println("joueur "+this.getNom()+" a fait un hexagone");
				} else if (table.verifPont(new Point(posX, posY), piece)) {
					this.setScore(this.getScore()+40);
					System.out.println("joueur "+this.getNom()+" a fait un pont");
				}
				return new Point(posX, posY);
			} else {
				return new Point(-1, -1);
			}
		} else { // coup cpu
			for (int i = 0; i < this.getMain().size(); i++) {
				for (Point ext : table.getExtremite()) {
					for (PieceTriomino pieceCpu : this.getMain().get(i).getAllSwipes()) {
						if (table.coupValide(jeu, pieceCpu, ext)) {

							table.getTable()[ext.getX()][ext.getY()] = pieceCpu;
							this.supprimerExtremites(jeu, table, ext.getX(), ext.getY());
							this.ajouterExtremites(jeu, table, ext.getX(), ext.getY());
							this.setScore(this.getScore()+this.getMain().get(i).valeur()); 
							this.getMain().remove(i);
							if (table.verifTripleHexagone(ext)) {
								this.setScore(this.getScore()+70);
							} else if (table.verifDoubleHexagone(ext)) {
								this.setScore(this.getScore()+60);
							} else if (table.verifHexagone(ext, 0) || table.verifHexagone(ext, 1)
									|| table.verifHexagone(ext, 2)) {
								this.setScore(this.getScore()+50);
							} else if (table.verifPont(ext, pieceCpu)) {
								this.setScore(this.getScore()+40);
							}
							return new Point(ext.getX(), ext.getY());
						}
					}
				}
			}
			return new Point(-1, -1);
		}
	}

	public boolean nePeutPasJouer(int jeu, ModeleTriomino table) {

		for (Point p : table.getExtremite()) {
			for (PieceTriomino piece : this.getAllPiecesSwipes()) {
				if (table.coupValide(jeu, piece, p)) {
					return false;
				}

			}
		}

		return true;

	}

}
