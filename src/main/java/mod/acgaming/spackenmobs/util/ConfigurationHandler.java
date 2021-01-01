package mod.acgaming.spackenmobs.util;

import net.minecraftforge.common.ForgeConfigSpec;

// Thanks to Girafi!
public class ConfigurationHandler
{
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final General GENERAL = new General(BUILDER);
	public static final Spawn SPAWN = new Spawn(BUILDER);
	public static final ForgeConfigSpec spec = BUILDER.build();

	public static class General
	{
		public final ForgeConfigSpec.IntValue jens_digest_time;
		public final ForgeConfigSpec.IntValue jens_search_distance;

		General(ForgeConfigSpec.Builder builder)
		{
			builder.push("General");

			builder.comment("Configure the time in seconds Jens needs to digest.");
			jens_digest_time = builder.defineInRange("jens_digest_time", 120, 1, 1200);

			builder.comment("Configure the maximum distance in blocks Jens can search.");
			jens_search_distance = builder.defineInRange("jens_search_distance", 16, 1, 64);

			builder.pop();
		}
	}

	public static class Spawn
	{
		public final ForgeConfigSpec.IntValue apored_min;
		public final ForgeConfigSpec.IntValue apored_max;
		public final ForgeConfigSpec.IntValue apored_weight;

		public final ForgeConfigSpec.IntValue bakamitai_creeper_min;
		public final ForgeConfigSpec.IntValue bakamitai_creeper_max;
		public final ForgeConfigSpec.IntValue bakamitai_creeper_weight;

		public final ForgeConfigSpec.IntValue drachenlord_min;
		public final ForgeConfigSpec.IntValue drachenlord_max;
		public final ForgeConfigSpec.IntValue drachenlord_weight;

		public final ForgeConfigSpec.IntValue friedrich_min;
		public final ForgeConfigSpec.IntValue friedrich_max;
		public final ForgeConfigSpec.IntValue friedrich_weight;

		public final ForgeConfigSpec.IntValue gisela_min;
		public final ForgeConfigSpec.IntValue gisela_max;
		public final ForgeConfigSpec.IntValue gisela_weight;

		public final ForgeConfigSpec.IntValue holzstammhuhn_min;
		public final ForgeConfigSpec.IntValue holzstammhuhn_max;
		public final ForgeConfigSpec.IntValue holzstammhuhn_weight;

		public final ForgeConfigSpec.IntValue islamist_min;
		public final ForgeConfigSpec.IntValue islamist_max;
		public final ForgeConfigSpec.IntValue islamist_weight;

		public final ForgeConfigSpec.IntValue jens_min;
		public final ForgeConfigSpec.IntValue jens_max;
		public final ForgeConfigSpec.IntValue jens_weight;

		public final ForgeConfigSpec.IntValue marcell_davis_min;
		public final ForgeConfigSpec.IntValue marcell_davis_max;
		public final ForgeConfigSpec.IntValue marcell_davis_weight;

		public final ForgeConfigSpec.IntValue mr_bean_min;
		public final ForgeConfigSpec.IntValue mr_bean_max;
		public final ForgeConfigSpec.IntValue mr_bean_weight;

		public final ForgeConfigSpec.IntValue mztewolf_min;
		public final ForgeConfigSpec.IntValue mztewolf_max;
		public final ForgeConfigSpec.IntValue mztewolf_weight;

		public final ForgeConfigSpec.IntValue schalker_min;
		public final ForgeConfigSpec.IntValue schalker_max;
		public final ForgeConfigSpec.IntValue schalker_weight;

		public final ForgeConfigSpec.IntValue smava_creeper_min;
		public final ForgeConfigSpec.IntValue smava_creeper_max;
		public final ForgeConfigSpec.IntValue smava_creeper_weight;

		Spawn(ForgeConfigSpec.Builder builder)
		{
			builder.push("Spawn Chances");
			builder.comment("Configure spawn weight & min/max group size. Set weight to 0 to disable.");

			apored_min = builder.defineInRange("apored_min", 1, 1, 64);
			apored_max = builder.defineInRange("apored_max", 1, 1, 64);
			apored_weight = builder.defineInRange("apored_weight", 60, 0, 200);

			bakamitai_creeper_min = builder.defineInRange("bakamitai_creeper_min", 1, 1, 64);
			bakamitai_creeper_max = builder.defineInRange("bakamitai_creeper_max", 1, 1, 64);
			bakamitai_creeper_weight = builder.defineInRange("bakamitai_creeper_weight", 60, 0, 200);

			drachenlord_min = builder.defineInRange("drachenlord_min", 1, 1, 64);
			drachenlord_max = builder.defineInRange("drachenlord_max", 1, 1, 64);
			drachenlord_weight = builder.defineInRange("drachenlord_weight", 60, 0, 200);

			friedrich_min = builder.defineInRange("friedrich_min", 1, 1, 64);
			friedrich_max = builder.defineInRange("friedrich_max", 1, 1, 64);
			friedrich_weight = builder.defineInRange("friedrich_weight", 8, 0, 200);

			gisela_min = builder.defineInRange("gisela_min", 1, 1, 64);
			gisela_max = builder.defineInRange("gisela_max", 1, 1, 64);
			gisela_weight = builder.defineInRange("gisela_weight", 8, 0, 200);

			holzstammhuhn_min = builder.defineInRange("holzstammhuhn_min", 1, 1, 64);
			holzstammhuhn_max = builder.defineInRange("holzstammhuhn_max", 1, 1, 64);
			holzstammhuhn_weight = builder.defineInRange("holzstammhuhn_weight", 8, 0, 200);

			islamist_min = builder.defineInRange("islamist_min", 1, 1, 64);
			islamist_max = builder.defineInRange("islamist_max", 1, 1, 64);
			islamist_weight = builder.defineInRange("islamist_weight", 60, 0, 200);

			jens_min = builder.defineInRange("jens_min", 1, 1, 64);
			jens_max = builder.defineInRange("jens_max", 1, 1, 64);
			jens_weight = builder.defineInRange("jens_weight", 8, 0, 200);

			marcell_davis_min = builder.defineInRange("marcell_davis_min", 1, 1, 64);
			marcell_davis_max = builder.defineInRange("marcell_davis_max", 1, 1, 64);
			marcell_davis_weight = builder.defineInRange("marcell_davis_weight", 60, 0, 200);

			mr_bean_min = builder.defineInRange("mr_bean_min", 1, 1, 64);
			mr_bean_max = builder.defineInRange("mr_bean_max", 1, 1, 64);
			mr_bean_weight = builder.defineInRange("mr_bean_weight", 60, 0, 200);

			mztewolf_min = builder.defineInRange("mztewolf_min", 1, 1, 64);
			mztewolf_max = builder.defineInRange("mztewolf_max", 1, 1, 64);
			mztewolf_weight = builder.defineInRange("mztewolf_weight", 8, 0, 200);

			schalker_min = builder.defineInRange("schalker_min", 1, 1, 64);
			schalker_max = builder.defineInRange("schalker_max", 1, 1, 64);
			schalker_weight = builder.defineInRange("schalker_weight", 60, 0, 200);

			smava_creeper_min = builder.defineInRange("smava_creeper_min", 1, 1, 64);
			smava_creeper_max = builder.defineInRange("smava_creeper_max", 1, 1, 64);
			smava_creeper_weight = builder.defineInRange("smava_creeper_weight", 60, 0, 200);

			builder.pop();
		}
	}
}