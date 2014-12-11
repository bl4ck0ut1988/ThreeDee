package threedee.pipeline;

import threedee.geometry.Fragment;

import java.util.ArrayList;

public interface Rasterization {
    ArrayList<Fragment> getFragments();
    int getScreenWidth();
    int getScreenHeight();

    void addPipelineListener(PipelineListener listener);
}
