package tppitweaks.util;


// TODO decide if keeping this
//@SideOnly(Side.CLIENT)
//public class CapeBufferDownload extends Thread
//{
//	public String username;
//
//	public String staticCapeUrl;
//	
//	public ResourceLocation resourceLocation;
//	
//	public ThreadDownloadImageData capeImage;
//
//	boolean downloaded = false;
//
//	public CapeBufferDownload(String name, String url) 
//	{
//		username = name;
//		staticCapeUrl = url;
//		
//		setDaemon(true);
//		setName("Cape Downlaod Thread");
//	}
//
//	@Override
//	public void run() 
//	{
//		try {
//			download();
//		} catch(Exception e) {}
//	}
//
//	private void download() 
//	{
//		try {
//			resourceLocation = new ResourceLocation("tppitweaks/" + StringUtils.stripControlCodes(username));
//			
//			Method method = TPPITweaksUtils.getPrivateMethod(AbstractClientPlayer.class, TPPITickHandler.AbstractClientPlayer_getDownloadImage, ResourceLocation.class, String.class, ResourceLocation.class, IImageBuffer.class);
//			Object obj = method.invoke(null, resourceLocation, staticCapeUrl, null, null);
//			
//			if(obj instanceof ThreadDownloadImageData)
//			{
//				capeImage = (ThreadDownloadImageData)obj;
//			}
//		} catch(Exception e) {}
//
//		downloaded = true;
//	}
//
//	public ThreadDownloadImageData getImage()
//	{
//		return capeImage;
//	}
//
//	public ResourceLocation getResourceLocation()
//	{
//		return resourceLocation;
//	}
//}