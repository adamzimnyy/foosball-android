package azi.foosball;

public enum Player {

	AZI("Adam", R.drawable.azi),
	DCY("Daniel", R.drawable.dcy),
	KKA("Karol",R.drawable.kka),
	MMI("Marcin",R.drawable.mmi),
	PGR("Piotrek",R.drawable.pgr),
	UGA("Ula", R.drawable.uga);

	String name;
	int image;

	Player(String name, int image) {

		this.name = name;
		this.image = image;
	}

	public String getName() {

		return name;
	}

	public int getImage() {

		return image;
	}
}
