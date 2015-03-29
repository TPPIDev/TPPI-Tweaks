package tppitweaks.util;


// FIXME capes? something else?
//public class TPPITickHandler implements ITickHandler
//{
//	public static Minecraft mc = FMLClientHandler.instance().getClient();
//	private static final String CAPE_URL = "http://assets.tterrag.com/TPPI/TPPIcape.png";
//	private Map<String, CapeBufferDownload> downloads = new HashMap<String, CapeBufferDownload>();
//
//	private URL usernames;
//	private boolean noInternet = false;
//
//	private ImmutableList<String> devs;
//
//	public static String[] AbstractClientPlayer_downloadImageCape = new String[] { "downloadImageCape", "field_110315_c", "c" };
//	public static String[] AbstractClientPlayer_locationCape = new String[] { "locationCape", "field_110313_e", "e" };
//	public static String[] AbstractClientPlayer_getDownloadImage = new String[] { "getDownloadImage", "func_110301_a", "a" };
//
//	public TPPITickHandler()
//	{
//		Scanner scan = null;
//
//		try
//		{
//			usernames = new URL("http://assets.tterrag.com/TPPI/capes.txt");
//			scan = new Scanner(usernames.openStream());
//		}
//		catch (Throwable pokemon)
//		{
//			pokemon.printStackTrace();
//		}
//
//		if (usernames == null || scan == null)
//		{
//			TPPITweaks.logger.warning("No internet connection, skipping cape download...");
//			noInternet = true;
//		}
//
//		if (!noInternet)
//		{
//			List<String> lines = new ArrayList<String>();
//			while (scan.hasNextLine())
//			{
//				lines.add(scan.nextLine());
//			}
//
//			scan.close();
//
//			ImmutableList.Builder<String> builder = new ImmutableList.Builder<String>();
//			builder.addAll(lines);
//
//			devs = builder.build();
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public void tickStart(EnumSet<TickType> type, Object... tickData)
//	{
//		if (!noInternet && ConfigurationHandler.allowCapes && mc.theWorld != null && mc.theWorld.getWorldTime() % 20 == 0 && !isPaused())
//		{
//			for (EntityPlayer entityPlayer : (List<EntityPlayer>) mc.theWorld.playerEntities)
//			{
//				if (entityPlayer instanceof AbstractClientPlayer)
//				{
//					AbstractClientPlayer player = (AbstractClientPlayer) entityPlayer;
//
//					if (player != null)
//					{
//						if (devs.contains(StringUtils.stripControlCodes(player.username)))
//						{
//							CapeBufferDownload download = downloads.get(player.username);
//
//							if (download == null)
//							{
//								download = new CapeBufferDownload(player.username, CAPE_URL);
//								downloads.put(player.username, download);
//
//								download.start();
//							}
//							else
//							{
//								if (!download.downloaded)
//								{
//									continue;
//								}
//
//								TPPITweaksUtils.setPrivateValue(player, download.getImage(), AbstractClientPlayer.class, AbstractClientPlayer_downloadImageCape);
//								TPPITweaksUtils.setPrivateValue(player, download.getResourceLocation(), AbstractClientPlayer.class, AbstractClientPlayer_locationCape);
//							}
//						}
//					}
//				}
//			}
//		}
//	}
//
//	@Override
//	public void tickEnd(EnumSet<TickType> type, Object... tickData)
//	{
//		// Do nothing
//	}
//
//	@Override
//	public EnumSet<TickType> ticks()
//	{
//		return EnumSet.of(TickType.CLIENT);
//	}
//
//	@Override
//	public String getLabel()
//	{
//		return "tppiTickHandler";
//	}
//
//	private boolean isPaused()
//	{
//		if (FMLClientHandler.instance().getClient().isSingleplayer() && !FMLClientHandler.instance().getClient().getIntegratedServer().getPublic())
//		{
//			GuiScreen screen = FMLClientHandler.instance().getClient().currentScreen;
//
//			if (screen != null && screen.doesGuiPauseGame())
//			{
//				return true;
//			}
//		}
//
//		return false;
//	}
//}
