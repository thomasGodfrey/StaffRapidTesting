package simcore.diagnosis;
import repast.simphony.context.Context;
import repast.simphony.data2.AggregateDataSource;
import repast.simphony.space.grid.Grid;

/**
 * @author nick
 *
 */
public class TrackAndTraceFalsePositivesRecorder implements AggregateDataSource {

	@Override
	public String getId() {
		return "TrackAndTraceFalsePositives";
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
		return TrackAndTraceApp.GetInstance().GetFalsePositives();
	}

	@Override
	public void reset() {}
}
