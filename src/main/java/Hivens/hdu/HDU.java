package Hivens.hdu;
import Hivens.hdu.Common.Registry.BlockRegistry;
import Hivens.hdu.Common.Registry.CreativeTabRegistry;
import Hivens.hdu.Common.Registry.ItemRegistry;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(HDU.MODID)
public class HDU {

    public static final String MODID = "hdu";

    public HDU() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Регистрация предметов
        ItemRegistry.register(modEventBus);
        // Регистрация блоков
        BlockRegistry.register(modEventBus);
        // Регистрация креативного меню
        CreativeTabRegistry.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }
}
