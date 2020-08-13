package mod.acgaming.spackenmobs.items;
import mod.acgaming.spackenmobs.Spackenmobs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSurstroemming extends Item
{
    public ItemSurstroemming()
    {
        setRegistryName("surstroemming");
        setUnlocalizedName(Spackenmobs.MODID + ".surstroemming");
        setCreativeTab(CreativeTabs.MISC);
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel()
    {
    	ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}