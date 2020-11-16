package simcore.diagnosis;

public class TrustRating {

	private Double rating;
	private Double timestamp;
	
	public TrustRating(Double pdblTimestamp, Double pdblrating) {
		this.timestamp = pdblTimestamp;
		this.rating = pdblrating;
	}

	public Double getTimestamp() {
		return timestamp;
	}

	public Double getRating() {
		return rating;
	}
}
