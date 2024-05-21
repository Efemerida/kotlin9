package com.project.kotlin9.ui.ref

import android.os.Bundle
import android.view.ActionMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.kotlin9.databinding.FragmentRefBinding


class RefFragment : Fragment() {
    private var _binding: FragmentRefBinding? = null
    private val binding get() = _binding!!
    private var actionMode: ActionMode? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRefBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val text="  \"online.106i9fm.ru:8000/106i9_mp3\"\n" +
                "  \"95.104.192.220:8001/radio2x2.ogg\"\n" +
                "  \"online.guberniya.tv:8000/32radio\"\n" +
                "  \"23.105.253.220:8000/bestmmx128.mp3\"\n" +
                "  \"bfm.hostingradio.ru:8004/fm\"\n" +
                "  \"icecast.vgtrk.cdnvideo.ru/capitalfmmp3\"\n" +
                "  \"hitster.hostingradio.ru:8010/comedyufa192.mp3\"\n" +
                "  \"listen4.myradio24.com/dixi\"\n" +
                "  \"ic7.101.ru:8000/s20\"\n" +
                "  \"a2.radioheart.ru:8007/live\"\n" +
                "  \"s07.radio-tochka.com:6455/;\"\n" +
                "  \"79.172.43.10:8000/radio1\"\n" +
                "  \"icecast.gtrk22.ru:8000/heartfm\"\n" +
                "  \"myradio24.org/atamanfm\"\n" +
                "  \"nashe3.hostingradio.ru/jazz-128.mp3\"\n" +
                "  \"yumfm.hostingradio.ru:8020/yumfm128.mp3\"\n" +
                "  \"air.unmixed.ru/lradio256\"\n" +
                "  \"stream.jingles.ru:8000/lite-radio-mp3-128\"\n" +
                "  \"onair.lipetskfm.ru:8000/love_hq\"\n" +
                "  \"streams.radiosarova.ru/mcradio\"\n" +
                "  \"s0.radioheart.ru:8000/RH23554\"\n" +
                "  \"stream.radiometro.ru:8230/;stream.mp3\"\n" +
                "  \"radio.gubernia.com:8000/radio3\"\n" +
                "  \"nostalgie.dagfm.ru:8000/online\"\n" +
                "  \"icecast.nstation.ru:8000/nstation_192_mp3\"\n" +
                "  \"radio-holding.ru:9000/rfm\"\n" +
                "  \"radio-tochka.com:6445/;stream.mp3\"\n" +
                "  \"online.rockarsenal.ru:8000/rockarsenal\"\n" +
                "  \"nashe1.hostingradio.ru/rock-256\"\n" +
                "  \"stream.jingles.ru:8000/rock-it-radio-mp3-128\"\n" +
                "  \"s0.radioheart.ru:8000/RH20436\"\n" +
                "  \"bp.koenig.ru:8000/Studio21_KLD.mp3\"\n" +
                "  \"212.107.192.144:8000/Radio256\"\n" +
                "  \"94.241.171.205:8000/stream\"\n" +
                "  \"radio.irkmedia.ru:10000/hit128\"\n" +
                "  \"misato.ru-hoster.com:8000/stream\"\n" +
                "  \"81.28.169.136:8000\"\n" +
                "  \"avtodor-tr.hostingradio.ru/avtodortr128.mp3\"\n" +
                "  \"ic7.101.ru:8000/v3_1\"\n" +
                "  \"195.191.78.200:8081/mp\"\n" +
                "  \"s0.radioheart.ru:8000/RH68610\"\n" +
                "  \"vgtrk15.hostingradio.ru:8011/vgtrk15128.mp3\"\n" +
                "  \"193.233.155.253:8000/control\"\n" +
                "  \"a1.radioheart.io:8001/altynkelfm\"\n" +
                "  \"188.19.13.33:8094/;\"\n" +
                "  \"online.alfafm.ru:8000/alfa_mp3\"\n" +
                "  \"62.152.59.3:8000/nkz\"\n" +
                "  \"62.152.59.3:8000/clubtouch\"\n" +
                "  \"46.17.43.197:8000/live.mp3\"\n" +
                "  \"195.191.78.200:8080/mp\"\n" +
                "  \"listen5.myradio24.com/ataman\"\n" +
                "  \"radio.mediacdn.ru/ashkadar.mp3\"\n" +
                "  \"91.203.171.138:8080/\"\n" +
                "  \"bp.koenig.ru:8000/Baltic_Plus_mp3_128.mp3\"\n" +
                "  \"89.250.214.162:8080/whiteradio.mp3\"\n" +
                "  \"misato.ru-hoster.com:8067/stream\"\n" +
                "  \"av.bimradio.ru:8066/bim_mp3_128k\"\n" +
                "  \"birskfm.ru:8000/birskfm\"\n" +
                "  \"icecast-bulteam.cdnvideo.ru/bolid128\"\n" +
                "  \"listen2.myradio24.com:9000/8286\"\n" +
                "  \"31.28.27.155:8000/Burfm\"\n" +
                "  \"buryadfm.hostingradio.ru:8018/buryadfm128.mp3\"\n" +
                "  \"nov.rlnk.ru:8000/radio.vanya\"\n" +
                "  \"misato.ru-hoster.com:8069/vradio\"\n" +
                "  \"91.214.128.125:64000/vatan\"\n" +
                "  \"radiovera.hostingradio.ru:8007/radiovera_128\"\n" +
                "  \"91.203.176.214:8000/vesnafm\"\n" +
                "  \"icecast.vgtrk.cdnvideo.ru/vestifm_dontr_mp3\"\n" +
                "  \"94.250.249.126:8000/radio\"\n" +
                "  \"37.139.33.202:8000/stream.mp3\"\n" +
                "  \"194.58.90.144:8025/xstream\"\n" +
                "  \"vladfm.ru:8000/vfm\"\n" +
                "  \"s0.radioheart.ru:8000/RH59163\"\n" +
                "  \"online.volgogradfm.ru:8000/vfm\"\n" +
                "  \"radio.volnafm.ru:8000/ekb\"\n" +
                "  \"31.134.47.205:8000/;stream.mp3\"\n" +
                "  \"player.vfmradio.ru/stream.ogg\"\n" +
                "  \"217.24.181.164:8000/stream.mp3\"\n" +
                "  \"sc1.ftw.monster/;stream.nsv\"\n" +
                "  \"188.120.251.66:8008/vostok_nalchik.ogg\"\n" +
                "  \"s0.radioheart.ru:8000/RH76759\"\n" +
                "  \"media.govoritmoskva.ru:8880/rufm.mp3\"\n" +
                "  \"91.189.162.134:8006/;stream.mp3\"\n" +
                "  \"voicemaikop.hostingradio.ru:8003/voicemaikop128.mp3\"\n" +
                "  \"radio.alania.net:8000/radiocity\"\n" +
                "  \"www.gorodfm.ru:8000/gorodfm\"\n" +
                "  \"online.gorvolna.ru:8000/gorvolna\"\n" +
                "  \"46.20.71.66:8000/fm\"\n" +
                "  \"213.137.248.211:8000/dls\"\n" +
                "  \"radio.omg56.ru:8000/Dacha_Oren64\"\n" +
                "  \"2beregafm.ru:8100/ber_FM.mp3\"\n" +
                "  \"air.radioday.fm/mp3\"\n" +
                "  \"prepros.radioday.fm/mp3forin_main\"\n" +
                "  \"prepros.radioday.fm/mp3rus_main\"\n" +
                "  \"online.jamfm.ru:8000/jam\"\n" +
                "  \"icecast.sibinformburo.cdnvideo.ru:8000/dipolfm\"\n" +
                "  \"dorognoe.hostingradio.ru:8000/radio\"\n" +
                "  \"radio.tatmedia.com:8800/Saba\"\n" +
                "  \"85.234.104.148:8003/\"\n" +
                "  \"birskfm.ru:8000/durfm\"\n" +
                "  \"free.radioheart.ru:8000/RH20613\"\n" +
                "  \"c28.radioboss.fm:8441/live\"\n" +
                "  \"free.radioheart.ru:8000/RH20606\"\n" +
                "  \"online-fefm.signaltv.net:8000/fefm\"\n" +
                "  \"195.88.63.114:8000/relax\"\n" +
                "  \"195.88.63.114:8000/zaoblakami\"\n" +
                "  \"zaycevfm.cdnvideo.ru/ZaycevFM_pop_128.mp3\"\n" +
                "  \"icecast-zvezda.mediacdn.ru/radio/zvezda/zvezda_128\"\n" +
                "  \"s.ar-ti.com:8000/zvuk\"\n" +
                "  \"46.166.65.29:8008/live.mp3\"\n" +
                "  \"ic5.101.ru:8000/v15_1\"\n" +
                "  \"stream.161.fm:8000/128\"\n" +
                "  \"radioigrim.ru:8000/live.mp3\"\n" +
                "  \"77.87.97.62:8086/ingradio\"\n" +
                "  \"online.intervolna.ru:8001/radio128\"\n" +
                "  \"online.intervolna.ru:8001/disco90\"\n" +
                "  \"online.intervolna.ru:8001/cold\"\n" +
                "  \"online.intervolna.ru:8001/drive\"\n" +
                "  \"online.intervolna.ru:8001/relax\"\n" +
                "  \"ice.interra.fm:8000/barix\"\n" +
                "  \"radio.dor-net.ru:8000/radio_stream.mp3\"\n" +
                "  \"iskatel.hostingradio.ru:8015/iskatel-128.mp3\"\n" +
                "  \"62.33.210.139:8000/\"\n" +
                "  \"a2.radioheart.ru:8019/RH66649\"\n" +
                "  \"radio.alania.net:8000/kvk\"\n" +
                "  \"kavkazhit.hostingradio.ru:8017/kavkazhit96.mp3\"\n" +
                "  \"icecast-studio21.cdnvideo.ru/KalynaK_1a\"\n" +
                "  \"a8.radioheart.ru:8026/RH75315\"\n" +
                "  \"83.239.140.18:8000/;stream.nsv\"\n" +
                "  \"online.karibu.ru/Karibu-Art\"\n" +
                "  \"online.radiokarnaval.ru:8000/karnaval\"\n" +
                "  \"5.53.124.23:8000/kasseta.mp3\"\n" +
                "  \"on.radio-cafe.ru:6045/;stream.nsv\"\n" +
                "  \"79.173.124.92:8000/stream\"\n" +
                "  \"radio.tatmedia.com:8800/kitapfm\"\n" +
                "  \"bookradio.hostingradio.ru:8069/fm\"\n" +
                "  \"online.kometa.fm:8000/live\"\n" +
                "  \"stream01.radiocon.ru:8000/live\"\n" +
                "  \"s0.radioheart.ru:8000/RH41064\"\n" +
                "  \"drh-node-04.dline-media.com/Kostroma-FM\"\n" +
                "  \"live.rzs.ru/ka.128.mp3\"\n" +
                "  \"icecast-radiokrasnodar.cdnvideo.ru/radiokrasnodar_1_mp3_320\"\n" +
                "  \"94.127.94.107:8000/KuzbassFM\"\n" +
                "  \"icecast.vgtrk.cdnvideo.ru/kulturafm_mp3_128kbps\"\n" +
                "  \"live.kunelradio.ru:8000/128.mp3\"\n" +
                "  \"92.241.19.186:8000/\"\n" +
                "  \"radio.dcatk.pro/radio.mp3\"\n" +
                "  \"212.107.192.130:8000/;stream.mp3\"\n" +
                "  \"60500.ru:8000/lenafm128.ogg\"\n" +
                "  \"online.radioletofm.ru:8000/radioletofm\"\n" +
                "  \"s1.radioheart.ru:8001/radiolivny\"\n" +
                "  \"listen4.myradio24.com/23737\"\n" +
                "  \"luki.ru:8000/lukifm\"\n" +
                "  \"85.172.206.196:8000/;\"\n" +
                "  \"metr12.ru:8000/metrfm\"\n" +
                "  \"94.190.77.56:8094/;stream.mp3\"\n" +
                "  \"23.105.253.220:8000/maksfm128.mp3\"\n" +
                "  \"rusradio.hostingradio.ru/maximum128.mp3\"\n" +
                "  \"metr12.ru:8000/mer\"\n" +
                "  \"mariafm.ru:8000/maria-fm-128.mp3\"\n" +
                "  \"live.radio7.su:9000/marusya_256\"\n" +
                "  \"radio-holding.ru:9000/marusya_default\"\n" +
                "  \"94.190.4.232:8000/Master.128\"\n" +
                "  \"radio.tvrts.ru:8000/mega.mp3\"\n" +
                "  \"online.radio-megapolis.ru:8000/megapolis_mp3\"\n" +
                "  \"icecast.mediaalliance.cdnvideo.ru/mediaalliance\"\n" +
                "  \"stream128.melodiafm.spb.ru:8000/melodia128\"\n" +
                "  \"62.152.59.3:8000/melomaniya\"\n" +
                "  \"listen7.myradio24.com/67798\"\n" +
                "  \"media.luga.ru:8002/live\"\n" +
                "  \"cdn.radiomixfm.ru:8000/mixfm\"\n" +
                "  \"radiomv.hostingradio.ru/radiomv128.mp3\"\n" +
                "  \"mirbelogorya.ru:8008/nonstop2\"\n" +
                "  \"cast.modnoeradio.ru:8000/mp128\"\n" +
                "  \"rusradio.hostingradio.ru/montecarlo128.mp3\"\n" +
                "  \"icecast.vgtrk.cdnvideo.ru:8000/moscowfm128\"\n" +
                "  \"94.181.181.72:8000/;stream.mp3\"\n" +
                "  \"radio.myudm.ru:10010/mp3\"\n" +
                "  \"metr12.ru:8000/MGH\"\n" +
                "  \"metr12.ru:8000/mtm\"\n" +
                
                "  \"drh-node-04.dline-media.com/gold\"\n" +
                "  \"109.232.105.226:8181/\"\n" +
                "  \"78.129.229.120:10741/\"\n" +
                "  \"nashe3.hostingradio.ru/nashe-128.mp3\"\n" +
                "  \"streams.radiosarova.ru/nashipesni\"\n" +
                
                "  \"176.99.73.47:8000/nikfm\"\n" +
                "  \"91.194.120.107:8000/128\"\n" +
                
                "  \"icecast-newradio.cdnvideo.ru/newradio3\"\n" +
                "  \"188.246.233.137:8000/stream\"\n" +
                "  \"194.58.102.16:8000/;stream.mp3\"\n" +
                "  \"a6.radioheart.ru:8028/RH36649\"\n" +
                "  \"213.177.106.78:8000/\"\n" +
                "  \"online.olimpfm.ru:8000/olimp_mp3\"\n" +
                "  \"drh-node-04.dline-media.com/orenfmorenburg\"\n" +
                "  \"orfeyfm.hostingradio.ru:8034/orfeyfm128.mp3\"\n" +
                "  \"l29.no-ip.org:8003/\"\n" +
                "  \"85.234.104.148:8002/\"\n" +
                "  \"1kradio.ru:8000/live\"\n" +
                "  \"streamer.radiovseti.ru:8000/192\"\n" +
                "  \"online.pilotfm.ru:8000/pilot\"\n" +
                "  \"ppr03.status-media.ru:8000/ppr128.mp3\"\n" +
                "  \"5.53.124.23:8000/ppr_90.mp3\"\n" +
                "  \"5.53.124.23:8000/ru-wave.mp3\"\n" +
                "  \"91.211.56.218:8080/piramida/nazarovo\"\n" +
                "  \"icecast-piterfm.cdnvideo.ru/piterfm\"\n" +
                
                "  \"193.107.103.41:1696/pobeda\"\n" +
                
                "  \"icecast-ext.dimfm.ru:8000/portal\"\n" +
                "  \"s1.radioheart.ru:8001/RH4122\"\n" +
                "  \"77.232.167.247:1007/admin\"\n" +
                "  \"62.16.41.97:8000/radioprivet\"\n" +
                "  \"62.16.41.97:8000/privetokt\"\n" +
                "  \"inhold.org:8000/primvolna-ussuriysk\"\n" +
                "  \"178.208.85.117:8000/puls\"\n" +
                "  \"cdn.radio1.news:8000/R1_Klin_89_7\"\n" +
                "  \"ic.radio10.live/stream.mp3\"\n" +
                "  \"37.113.131.86:8000/100fm\"\n" +
                "  \"streams.radiosarova.ru/fm102i3\"\n" +
                "  \"62.183.34.109:8000/radio107.mp3?1594418902549\"\n" +
                "  \"212.19.18.133:8000/stream\"\n" +
                "  \"81.23.194.18:8010/radio3.mp3\"\n" +
                "  \"195.112.119.187:8000/Radio40\"\n" +
                "  \"radio-holding.ru:9000/50\"\n" +
                "  \"84.242.241.66:8000/;stream.nsv\"\n" +
                "  \"91.202.68.50:8000/;stream.mp3\"\n" +
                "  \"live.radio7.su:9000/radio7_256\"\n" +
                "  \"radio7server.streamr.ru:8040/radio7128.mp3\"\n" +
                "  \"online.99-1.ru:8000/99i1_mp3\"\n" +
                "  \"radio05.ru:8000/radio_dagestan_128\"\n" +
                "  \"icecast.vgtrk.cdnvideo.ru/moscowtalk128\"\n" +
                
                "  \"a7.radioheart.ru:8008/radionoginsk\"\n" +
                "  \"c2.radioboss.fm:8787/live\"\n" +
                "  \"stream.pifm.ru/mp3\"\n" +
                "  \"live.podsolnuh.media:8000/radio\"\n" +
                
                "  \"icecast.vgtrk.cdnvideo.ru/rrzonam_mp3_128kbps\"\n" +
                "  \"radio.jingles.ru:8000/rostovradio\"\n" +
                "  \"194.28.241.173:8000/khs.mp3\"\n" +
                "  \"hit.trkeurasia.ru:8000/hit128\"\n" +
                "  \"188.93.129.110:8000/fm1018\"\n" +
                "  \"online.radio-n.ru:8000/radio\"\n" +
                "  \"online1.gkvr.ru:8000/radiola_eka_128.mp3\"\n" +
                "  \"online1.gkvr.ru:8000/radiola_srt_128.mp3\"\n" +
                "  \"icecast.radonezh.cdnvideo.ru:8000/rad128\"\n" +
                "  \"radio.ramradio.ru:8000/boss\"\n" +
                "  \"live.radio7.su:9000/deti\"\n" +
                "  \"62.192.226.178:8000/stream.ogg\"\n" +
                "  \"radio-srv1.11one.ru/record192k.mp3\"\n" +
                "  \"relaxfm72.ru:8000/stream\"\n" +
                "  \"retroserver.streamr.ru:8043/retro256.mp3\"\n" +
                "  \"retro70.hostingradio.ru:8025/retro70-128.mp3\"\n" +
                "  \"retro80.hostingradio.ru:8025/retro80-128.mp3\"\n" +
                "  \"retro90.hostingradio.ru:8025/retro90-128.mp3\"\n" +
                "  \"cast.modnoeradio.ru:8000/live\"\n" +
                "  \"85.234.36.51:8000/radio\"\n" +
                "  \"listen1.myradio24.com/2761\"\n" +
                "  \"online.holdingfm.ru:8010/rushit\"\n" +
                "  \"87.244.47.90:8000/Rus\"\n" +
                "  \"radio-holding.ru:9000/rus\"\n" +
                "  \"stv-radio.ru:8000/STV-Radio128k\"\n" +
                "  \"85.236.172.22:8000/maximum\"\n" +
                "  \"streams.radiosarova.ru/radiosarovfm\"\n" +
                "  \"31.200.226.167:10102/;\"\n" +
                "  \"91.201.117.219:8000/stream\"\n" +
                "  \"silverrain.hostingradio.ru/silver128.mp3\"\n" +
                "  \"online.radioc.ru:8000/radioc\"\n" +
                "  \"185.108.196.182:8090/HQ\"\n" +
                "  \"stream.radiosibir.ru:8090/kr2\"\n" +
                "  \"stream.radiosibir.ru:8090/orenburg\"\n" +
                "  \"stream.radiosibir.ru:8090/ulanude\"\n" +
                "  \"195.191.130.125:8000/sigma\"\n" +
                "  \"online.100i6fm.ru:8000/city_mp3\"\n" +
                "  \"solikamskfm.ru:8000/\"\n" +
                "  \"solfm.ru:8000/solfm90\"\n" +
                "  \"solfm.ru:8000/chill128\"\n" +
                "  \"solfm.ru:8000/dance128\"\n" +
                "  \"solfm.ru:8000/deep128\"\n" +
                "  \"solfm.ru:8000/online128\"\n" +
                "  \"solfm.ru:8000/rap128\"\n" +
                "  \"solfm.ru:8000/rock128\"\n" +
                "  \"62.16.41.97:8000/dnb128\"\n" +
                "  \"62.16.41.97:8000/lofi_128\"\n" +
                "  \"62.16.41.97:8000/phonk_128\"\n" +
                
                "  \"online.sputnikfm.ru:8000/sputnik_mp3\"\n" +
                "  \"online.sputnik107.ru:8000/sputnik107\"\n" +
                
                
                "  \"misato.ru-hoster.com:8027/stream\"\n" +
                "  \"91.189.162.134:8008/;stream.mp3\"\n" +
                "  \"85.95.185.19:8000/live\"\n" +
                "  \"icecast.stranafm.cdnvideo.ru/stranafm\"\n" +
                "  \"listen1.myradio24.com/6666\"\n" +
                "  \"91.197.207.171:8094/;\"\n" +
                "  \"176.118.220.124:8010/radio\"\n" +
                "  \"listen7.myradio24.com:9000/58828\"\n" +
                "  \"icecast.ntrk21.ru:8000/tavan\"\n" +
                "  \"radio.tatmedia.com:8800/tartipfm\"\n" +
                "  \"icecast-tvoyavolna.cdnvideo.ru/tvoyavolna\"\n" +
                "  \"radio.mediacdn.ru/tihiy_don.mp3\"\n" +
                "  \"176.65.38.3:8000/blagovest\"\n" +
                "  \"178.237.176.8:18000/minvody\"\n" +
                "  \"hit.trkeurasia.ru:8000/dotru128\"\n" +
                "  \"95.182.123.24:8000/transmit\"\n" +
                "  \"streams.radiosarova.ru/radiotransmit\"\n" +
                "  \"listen6.myradio24.com:9000/1102\"\n" +
                "  \"s0.radioheart.ru:8000/RH41106\"\n" +
                "  \"l29.no-ip.org:8002/\"\n" +
                "  \"85.234.104.148:8001/\"\n" +
                "  \"185.14.69.136:8000/radioNUD\"\n" +
                "  \"62.192.226.178:8001/stream.ogg\"\n" +
                "  \"sc.ultima.fm:8001/stream/1/stream.mp3\"\n" +
                "  \"23.105.253.220:8000/ultrammx128.mp3\"\n" +
                "  \"online.ulskfm.ru:8000/104i2_mp3\"\n" +
                "  \"drh-node-04.dline-media.com/urupinsk.fm\"\n" +
                "  \"radio.ufaley.su:8000/stream.mp3\"\n" +
                "  \"radio.mediacdn.ru/sputnik_fm.mp3\"\n" +
                "  \"radio.mediacdn.ru/fm_na_donu.mp3\"\n" +
                "  \"rusradio.hostingradio.ru/hitfm128.mp3\"\n" +
                "  \"navi.denel85.ru:8005/hit-navigator.mp3\"\n" +
                "  \"s0.radioheart.ru:8000/RH24958\"\n" +
                "  \"icecast.ntrk21.ru:8000/nacradio\"\n" +
                "  \"chanson.hostingradio.ru:8041/chanson128.mp3\"\n" +
                "  \"listen7.myradio24.com/bolshevek\"\n" +
                "  \"spb.radioshock.ru/radioshock\"\n" +
                "  \"choco.hostingradio.ru:10010/fm\"\n" +
                "  \"193.254.227.55:8000/Radio-Inet\"\n" +
                "  \"159.253.18.97:8000/128kbit.mp3\"\n" +
                "  \"emgspb.hostingradio.ru/eldoradio128.mp3\"\n" +
                "  \"hermitage.hostingradio.ru/hermitage128.mp3\"\n" +
                "  \"ugra.cdnradio.ru/radiougra\"\n" +
                "  \"misato.ru-hoster.com:8024/stream\"\n" +
                "  \"radio.mediacdn.ru/uldash.mp3\"\n" +
                "  \"87.244.47.90:8000/rh\"\n" +
                "  \"icecast-vgtrk.cdnvideo.ru/unost_mp3_128kbps\"\n" +
                "  \"91.195.86.238:8000/live\""
        binding.textView.text=text
//        binding.textView.setMovementMethod(ScrollingMovementMethod())
        return root
    }
}