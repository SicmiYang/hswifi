
package com.nfyg.hswx.fwinterface;

/**
 * bus 管理类
 *   
 * @version [版本号,2014年3月3日]
 */
public interface IBusManager
{

    /**
     * 新增bus
     * 
     * @method [addBus]
     * @param busName
     * @param bus
     * @retruntype [void]
     * @exception
     */
    public void addBus(String busName, IBus bus);

    /**
     * 得到bus
     * 
     * @method [getBus]
     * @param busName
     * @return
     * @retruntype [IBus]
     * @exception
     */
    public IBus getBus(String busName);

    /**
     * 删除一个bus
     * 
     * @method [removeBus]
     * @param busName
     * @retruntype [void]
     * @exception
     */
    public void removeBus(String busName);

    /**
     * 删除所有bus
     * 
     * @method [removeAll]
     * @retruntype [void]
     * @exception
     */
    public void removeAll();
}
