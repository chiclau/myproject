
/**
 * Class: SuperMap.Layer.AllTDTLayer
 * 天地图图层类。
 *     通过向天地图服务器发送请求得到天地图的图层。
 *
 * Inherits from:
 *  - <SuperMap.CanvasLayer>
 */

SuperMap.Layer.AllTDTLayer = SuperMap.Class(SuperMap.CanvasLayer, {

    /**
     * Property: name
     * {String} 图层标识名称。
     */
    name: "AllTDTLayer",
        
    //定义URL模板
    url: {
        "tdt_vec": "http://t0.tianditu.com/DataServer?T=vec_c&x=${x}&y=${y}&l=${z}",
        "tdt_cva": "http://t0.tianditu.com/DataServer?T=cva_c&x=${x}&y=${y}&l=${z}",
        "tdt_img": "http://t0.tianditu.com/DataServer?T=img_c&x=${x}&y=${y}&l=${z}",
        "tdt_cia": "http://t0.tianditu.com/DataServer?T=cia_c&x=${x}&y=${y}&l=${z}",

        "sheng_vec": "http://www.dzmap.cn/OneMapServer/rest/services/vector_service/MapServer/tile/${z}/${y}/${x}",
        "sheng_cva": "http://www.dzmap.cn/OneMapServer/rest/services/vector_ant/MapServer/tile/${z}/${y}/${x}",
        "sheng_img": "http://www.dzmap.cn/OneMapServer/rest/services/img_service/MapServer/tile/${z}/${y}/${x}",
        "sheng_cia": "http://www.dzmap.cn/OneMapServer/rest/services/img_ant/MapServer/tile/${z}/${y}/${x}",

        "shi_vec": "http://www.csmap.gov.cn/arcgis/rest/services/vmap_pub/MapServer/tile/${z}/${y}/${x}",
        "shi_cva": "http://www.csmap.gov.cn/arcgis/rest/services/vmapzj_pub/MapServer/tile/${z}/${y}/${x}",
        "shi_img": "http://www.csmap.gov.cn/arcgis/rest/services/yingxiangditu/MapServer/tile/${z}/${y}/${x}",
        "shi_cia": "http://www.csmap.gov.cn/arcgis/rest/services/yingxiangdituzhuji/MapServer/tile/${z}/${y}/${x}"
        },
        
    
    zOffset: {    /*在线的和离线的下面的设置不一样，离线的由于已经同意了文件夹命名，在文件夹命名中已经纠正了偏移量，所以下面的要加上1*/
        
        /*在线  省级：江西：1 湖南：0*/
        "tdt_vec":  1,
        "tdt_cva":  1,
        "tdt_img":  1,
        "tdt_cia":  1,
        "sheng_vec":0,
        "sheng_cva":0,
        "sheng_img":0,
        "sheng_cia":0,
        "shi_vec":  0,
        "shi_cva":  0,
        "shi_img":  0,  //娄底影像很奇葩
        "shi_cia":  0
        },
    
    /**
     * Constructor: SuperMap.Layer.AllTDTLayer
     * 
     *
     * Parameters:
     * options - {Object}  附加到图层属性上的可选项。
     */
    initialize: function (options) {
        
        //alert(options);
        //resolutions.push(1.40625/2/Math.pow(2,i));
        var me = this;       
        options = SuperMap.Util.extend({
            //useCanvas: true,
            transitionEffect: "resize", 
            tileLoadingDelay: 1,
            resolutions:[  
                    /*0.703125,          //180/256
                    0.3515625,
                    0.17578125,*/
                    0.087890625,
                    0.0439453125,
                    0.02197265625,
                    0.010986328125,
                    0.0054931640625,
                    0.00274658203125,
                    0.001373291015625,
                    0.0006866455078125,  //
                    0.00034332275390625,
                    0.000171661376953125/*,
                    0.0000858306884765625,
                    0.00004291534423828125,
                    0.000021457672119140625,
                    0.0000107288360595703125/*,
                    0.00000536441802978515625,
                    0.000002682209014892578125,
                    0.0000013411045074462890625*/
                ],
                maxExtent: new SuperMap.Bounds(-180, -90, 180, 90),dpi:96
        }, options);
        if(options.name){
            me.name = options.name;
        }
        if(options.url){
            me.url = options.url;
        }
        
        SuperMap.CanvasLayer.prototype.initialize.apply(me, [me.name, me.url, null, options]);
    },
    
    //为什么clone函数会有两个，规范吗？
    clone: function (obj) {
        var me = this;
        if (obj == null) {
            obj = new SuperMap.AllTDTLayer({name:obj.name, layerType: obj.layerType, transitionEffect: "resize"});
        }

        obj = SuperMap.CanvasLayer.prototype.clone.apply(me, [obj]);

        return obj;
    },
    
    /**
     * APIMethod: destroy
     * 解构AllTDTLayer类，释放资源。  
     */
    destroy: function () {
        var me = this;
        SuperMap.CanvasLayer.prototype.destroy.apply(me, arguments);
        me.name = null;
        me.url = null;
    },

    /**
     * APIMethod: clone
     * 创建当前图层的副本。
     *
     * Parameters:
     * obj - {Object} 
     *
     * Returns:
     * {<SuperMap.Layer.AllTDTLayer>} 
     */
    clone: function (obj) {
        var me = this;
        if (obj == null) {
            obj = new SuperMap.Layer.AllTDTLayer(
                me.name, me.url, me.getOptions());
        }
       
        obj = SuperMap.CanvasLayer.prototype.clone.apply(me, [obj]);

        return obj;
    },
    
    getTileUrl: function (xyz) {
        var me = this;
        var tileUrl = me.url;
        var x = xyz.x;
        var y = xyz.y;

        var z = xyz.z;

        var startfrom = 3;
        if(this.isLabel){
            if(me.layerType=="vec"){
                if(z < 14){  //1-14级，数组中0-13
                    tileUrl = tileUrl.tdt_cva; 
                    z = startfrom + xyz.z+me.zOffset.tdt_cva;
                } else if( z < 17){  //15-17级，数组中14-16
                   tileUrl = tileUrl.sheng_cva;
                   z = startfrom + xyz.z+me.zOffset.sheng_cva;
                } else {  //18-20级，数组中17-19
                   tileUrl = tileUrl.shi_cva;
                   z = startfrom + xyz.z+me.zOffset.shi_cva;
                }
            } else if(me.layerType=="img"){
                if(z < 14){  //1-14级，数组中0-13
                    tileUrl = tileUrl.tdt_cia; 
                    z = startfrom + xyz.z+me.zOffset.tdt_cia;
                } else if( z < 17){  //15-17级，数组中14-16
                   tileUrl = tileUrl.sheng_cia;
                   z = startfrom + xyz.z+me.zOffset.sheng_cia;
                } else {  //18-20级，数组中17-19
                   tileUrl = tileUrl.shi_cia;
                   z = startfrom + xyz.z+me.zOffset.shi_cia;
                }
            }
        } else {
            if(this.layerType=="vec"){
                if(z < 14){  //1-14级，数组中0-13
                    tileUrl = tileUrl.tdt_vec; 
                    z = startfrom + xyz.z+me.zOffset.tdt_vec;
                } else if( z < 17){  //15-17级，数组中14-16
                   tileUrl = tileUrl.sheng_vec;
                   z = startfrom + xyz.z+me.zOffset.sheng_vec;
                }else {  //18-20级，数组中17-19
                   tileUrl = tileUrl.shi_vec;
                   z = startfrom + xyz.z+me.zOffset.shi_vec;
                }
            } else if(this.layerType=="img"){
                if(z < 14){  //1-14级，数组中0-13
                    tileUrl = tileUrl.tdt_img; 
                    z = startfrom + xyz.z+me.zOffset.tdt_img;
                } else if( z < 17){  //15-17级，数组中14-16
                   tileUrl = tileUrl.sheng_img;
                   z = startfrom + xyz.z+me.zOffset.sheng_img;
                } else {  //18-20级，数组中17-19
                   tileUrl = tileUrl.shi_img;
                   z = startfrom + xyz.z+me.zOffset.shi_img;
                }
            }
        }
        /*SuperMap 自定义类型扩展, 包含string, number, function and array.
        *SuperMap.String 字符串操作的一系列常用扩展函数.
        */
        tileUrl = SuperMap.String.format(tileUrl, {
            x: x,
            y: y,
            z: z
        });
        return tileUrl;
    },
    
    CLASS_NAME: "SuperMap.Layer.AllTDTLayer"
});