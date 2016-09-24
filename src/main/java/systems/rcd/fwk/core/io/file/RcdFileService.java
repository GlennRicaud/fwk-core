package systems.rcd.fwk.core.io.file;

import java.nio.file.Path;
import java.util.function.Consumer;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;

public interface RcdFileService
    extends RcdService
{
    static void listSubPaths( final Path path, final Consumer<Path> pathConsumer )
    {
        RcdContext.getService( RcdFileService.class ).instListSubPaths( path, pathConsumer );
    }

    static void deleteDirectory( final Path directory )
    {
        RcdContext.getService( RcdFileService.class ).instDeleteDirectory( directory );
    }

    static long getDirectorySize( final Path directory )
    {
        return RcdContext.getService( RcdFileService.class ).instGetDirectorySize( directory );
    }

    void instListSubPaths( final Path path, final Consumer<Path> pathConsumer );
    
    void instDeleteDirectory( final Path directory );

    long instGetDirectorySize( final Path directory );
}
