package it.polito.tdp.itunes.model;

public class Album implements Comparable<Album>{
	private Integer albumId;
	private String title;
	private double totPrezzo;
	
	public Album(Integer albumId, String title, double totPrezzo) {
		super();
		this.albumId = albumId;
		this.title = title;
		this.totPrezzo = totPrezzo;
	}

	public Integer getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((albumId == null) ? 0 : albumId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		if (albumId == null) {
			if (other.albumId != null)
				return false;
		} else if (!albumId.equals(other.albumId))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return title +" "+totPrezzo;
	}

	public double getTotPrezzo() {
		return totPrezzo;
	}

	public void setTotPrezzo(double totPrezzo) {
		this.totPrezzo = totPrezzo;
	}

	@Override
	public int compareTo(Album o) {

		return this.title.compareTo(o.getTitle());
	}
	
	
	
}
