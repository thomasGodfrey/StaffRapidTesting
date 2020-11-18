package simcore.diagnosis;
import repast.simphony.context.Context;
import repast.simphony.data2.AggregateDataSource;
import repast.simphony.space.grid.Grid;

/**
 * @author nick
 *
 */
public class TrackAndTraceFalseNegativesRecorder implements AggregateDataSource {

	@Override
	public String getId() {
		return "TrackAndTraceFalseNegatives";
	}

	@Override
	public Class<?> getDataType() {
		return double.class;
	}

	@Override
	public Class<?> getSourceType() {
		return TrackAndTraceApp.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object get(Iterable<?> objs, int size) {
		return TrackAndTraceApp.GetInstance().GetFalseNegatives();
	}

	@Override
	public void reset() {}
}
