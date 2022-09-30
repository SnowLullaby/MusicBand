package parsers;

import models.MusicBand;

import javax.xml.bind.annotation.*;
import java.util.Vector;

@XmlRootElement(name = "musicBandCollection")
public class MusicBandCollectionStructureXML {
    @XmlElement(name = "maxID")
    public Long maxID;
    @XmlElement(name = "band")
    public Vector<MusicBand> collection;
}
