/*
 * Copyright 2013, Institute of Geological & Nuclear Sciences Ltd or
 * third-party contributors as indicated by the @author tags.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */



package nz.org.geonet.simplequakeml.domain;

//~--- non-JDK imports --------------------------------------------------------

import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

//~--- JDK imports ------------------------------------------------------------

/**
 * Created with IntelliJ IDEA.
 * @author: Geoff Clitheroe
 * Date: 5/2/13
 * Time: 4:14 PM
 */
public class DeserializeTest {

    /**
     * Test the SimpleXML mappings and deserialization.
     * @throws Exception
     */
    @Test
    public void deserializeSimple() throws Exception {
        Serializer serializer = new Persister();
        File source = new File("src/test/resources/2013p321497-simple.xml");
        Event event = serializer.read(Event.class, source);

        assertEquals("publicID", "smi:scs/0.6/2013p321497", event.getPublicID());
        assertEquals("eventID", "2013p321497", event.getEventID());
        assertEquals("type", "blast", event.getType());
        assertEquals("agency", "WEL(GNS_Primary)", event.getAgencyID());
        assertEquals("time", "2013-04-29T22:53:41.038153Z", event.getTimeString());
        assertEquals("Joda time", "2013-04-29T22:53:41.038Z", event.getTime().withZone(DateTimeZone.UTC).toString());
        assertEquals("latitude", -40.079396f, event.getLatitude(), 0.000001);
        assertEquals("longitude", 176.44021f, event.getLongitude(), 0.00001);
        assertEquals("depth", 35.585938f, event.getDepth(), 0.000001);
        assertEquals("magnitude", 2.1897815f, event.getMagnitude(), 0.000001);
        assertEquals("magnitude type", "M", event.getMagnitudeType());

        Pick pick = event.getPicks().get(0);

        assertEquals("phase", "P", pick.getPhase());
        assertEquals("mode", "automatic", pick.getMode());
        assertNull("status", pick.getStatus());
        assertEquals("time", "2013-04-29T22:53:47.168392Z", pick.getTimeString());
        assertEquals("joda time", "2013-04-29T22:53:47.168Z", pick.getTime().withZone(DateTimeZone.UTC).toString());
        assertEquals("weight", 1.870411f, pick.getWeight(), 0.000001);
        assertEquals("network", "NZ", pick.getNetwork());
        assertEquals("station", "WPHZ", pick.getStation());
        assertEquals("location", "10", pick.getLocation());
        assertEquals("channel", "EHZ", pick.getChannel());
    }

    @Test
    public void deserializeSimpleEventsNulls() throws Exception {
        Serializer serializer = new Persister();
        File source = new File("src/test/resources/2013p321497-simple-nulls.xml");
        Event event = serializer.read(Event.class, source);

        assertEquals("Empty pick list", 0, event.getPicks().size());
        assertNull("type", event.getType());
        assertNull("magnitude Type", event.getMagnitudeType());
        assertNull("agency", event.getAgencyID());
    }

    @Test
    public void deserializeSimplePickNulls() throws Exception {
        Serializer serializer = new Persister();
        File source = new File("src/test/resources/2013p321497-simple-pick-nulls.xml");
        Event event = serializer.read(Event.class, source);
        Pick pick = event.getPicks().get(0);

        assertNull("mode", pick.getMode());
        assertNull("status", pick.getStatus());
        assertEquals("weight", 0.0, pick.getWeight(), 0.001);
        assertNull("location", pick.getLocation());
        assertNull("channel", pick.getChannel());
    }
}
