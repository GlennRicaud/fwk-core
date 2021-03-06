package systems.rcd.fwk.core.format.properties.impl;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import systems.rcd.fwk.core.exc.RcdException;
import systems.rcd.fwk.core.format.properties.RcdPropertiesService;
import systems.rcd.fwk.core.io.file.RcdTextFileService;
import systems.rcd.fwk.core.io.file.params.RcdReadTextFileParams;

public class RcdSimplePropertiesService
    implements RcdPropertiesService
{
    //Space after property value is ignore and backslashes are ignored
    private final static Pattern PATTERN = Pattern.compile( "^\\s*([^\\s#!]+)\\s*=\\s*(\\S+)" );

    @Override
    public Map<String, String> instRead( final Path path )
    {
        final Map<String, String> properties = new HashMap<>();
        try
        {
            final RcdReadTextFileParams textFileParams = RcdReadTextFileParams.newBuilder().
                path( path ).
                linesConsumer( lines -> {
                    lines.forEach( line -> {
                        final Matcher matcher = PATTERN.matcher( line );
                        if ( matcher.find() )
                        {
                            properties.put( matcher.group( 1 ), matcher.group( 2 ) );
                        }
                    } );
                } ).
                build();
            RcdTextFileService.read( textFileParams );
            return properties;
        }
        catch ( Exception e )
        {
            throw new RcdException( "Error while reading properties document", e );
        }
    }
}
