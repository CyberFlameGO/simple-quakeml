package nz.org.geonet.simplequakeml;

//~--- non-JDK imports --------------------------------------------------------

import nz.org.geonet.simplequakeml.domain.Event;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

//~--- JDK imports ------------------------------------------------------------

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Geoff Clitheroe
 * Date: 5/3/13
 * Time: 11:32 AM
 */
public class QuakeML_RT_1_2Test {
    @Test
    public void testRead() throws Exception {
        File source = new File("src/test/resources/2013p321497.xml");
        SimpleQuakeML simpleQuakeML = new QuakeML_RT_1_2();
        Event event = simpleQuakeML.read(new FileInputStream(source));

        assertEquals("publicID", "smi:scs/0.6/2013p321497", event.getPublicID());
        assertNull("type", event.getType());
        assertEquals("agency", "WEL(GNS_Primary)", event.getAgencyID());
        assertEquals("time", "2013-04-29T22:53:41.038153Z", event.getTimeString());
        assertEquals("latitude", -40.079396f, event.getLatitude(), 0.000001);
        assertEquals("longitude", 176.44021f, event.getLongitude(), 0.00001);
        assertEquals("depth", 35.585938f, event.getDepth(), 0.000001);
        assertEquals("magnitude", 2.1897815f, event.getMagnitude(), 0.000001);
        assertEquals("magnitude type", "M", event.getMagnitudeType());
    }

    @Test
    public void testDownload() throws Exception {
        SimpleQuakeML simpleQuakeML = new QuakeML_RT_1_2();
        Event event = simpleQuakeML.read("http://quakeml.geonet.org.nz/quakeml-rt/1.2/2013p325188.xml");

        assertEquals("public id", "2013p325188", event.getEventID());
    }
}
