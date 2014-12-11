package threedee.pipeline;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Pixels {
    BufferedImage getPixels();
    void setBackgroundColor(Color color);

    void setDepthCueingEnabled(boolean enabled);
    void setTransparencyEnabled(boolean enabled);

    void addPipelineListener(PipelineListener listener);
}
