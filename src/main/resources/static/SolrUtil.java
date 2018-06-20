package templates;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

/**
 * @（#）:SolrUtil.java
 * @description:
 * @author: jiaosen 2018/5/8
 * @version: Version 1.0
 */
public class SolrUtil {
    private static SolrClient solrClient;

    private static String url;

    static {
        url = "http://localhost:8983/solr/jiaos";
        solrClient = new HttpSolrClient.Builder(url).build();
    }

    /**
     * 保存或者更新solr数据
     *
     * @param solrEntity
     * @param <T>
     * @return
     */
    public static <T> boolean saveSolrResource(T solrEntity) {
        DocumentObjectBinder binder = new DocumentObjectBinder();
        SolrInputDocument doc = binder.toSolrInputDocument(solrEntity);

        try {
            solrClient.add(doc);
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 删除solr数据
     *
     * @return
     */
    public static boolean removeSolrData(String id) {
        try {
            solrClient.deleteById(id);
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static QueryResponse query(String keyword) throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery(keyword);
        QueryResponse rsp = solrClient.query(query);
        return rsp;
    }

    public static void main(String[] args) {
        int i = 0xAB;
        System.out.println(i);
//        System.out.println("v=0\r\no=- 506981176923019886 2 IN IP4 127.0.0.1\r\ns=-\r\nt=0 0\r\na=group:BUNDLE audio video\r\na=msid-semantic: WMS ARDAMS\r\nm=audio 9 UDP/TLS/RTP/SAVPF 111 103 9 102 0 8 106 105 13 127 126\r\nc=IN IP4 0.0.0.0\r\na=rtcp:9 IN IP4 0.0.0.0\r\na=ice-ufrag:gFSbyMd3fWii178c\r\na=ice-pwd:zc15qPiI5Xy6d7aU5ViII3Tt\r\na=fingerprint:sha-256 A4:A8:92:F5:88:D3:FE:20:5D:C8:20:4F:84:6A:12:1C:AF:4A:81:18:0B:F9:4B:EB:BC:96:F1:55:3A:7B:59:2D\r\na=setup:actpass\r\na=mid:audio\r\na=extmap:1 urn:ietf:params:rtp-hdrext:ssrc-audio-level\r\na=extmap:3 http://www.webrtc.org/experiments/rtp-hdrext/abs-send-time\r\na=sendrecv\r\na=rtcp-mux\r\na=rtpmap:111 opus/48000/2\r\na=fmtp:111 minptime=10; useinbandfec=1\r\na=rtpmap:103 ISAC/16000\r\na=rtpmap:9 G722/8000\r\na=rtpmap:102 ILBC/8000\r\na=rtpmap:0 PCMU/8000\r\na=rtpmap:8 PCMA/8000\r\na=rtpmap:106 CN/32000\r\na=rtpmap:105 CN/16000\r\na=rtpmap:13 CN/8000\r\na=rtpmap:127 red/8000\r\na=rtpmap:126 telephone-event/8000\r\na=maxptime:60\r\na=ssrc:1255477924 cname:HXv7l5kKsobv+2Ry\r\na=ssrc:1255477924 msid:ARDAMS ARDAMSa0\r\na=ssrc:1255477924 mslabel:ARDAMS\r\na=ssrc:1255477924 label:ARDAMSa0\r\nm=video 9 UDP/TLS/RTP/SAVPF 100 116 117 96\r\nc=IN IP4 0.0.0.0\r\na=rtcp:9 IN IP4 0.0.0.0\r\na=ice-ufrag:gFSbyMd3fWii178c\r\na=ice-pwd:zc15qPiI5Xy6d7aU5ViII3Tt\r\na=fingerprint:sha-256 A4:A8:92:F5:88:D3:FE:20:5D:C8:20:4F:84:6A:12:1C:AF:4A:81:18:0B:F9:4B:EB:BC:96:F1:55:3A:7B:59:2D\r\na=setup:actpass\r\na=mid:video\r\na=extmap:2 urn:ietf:params:rtp-hdrext:toffset\r\na=extmap:3 http://www.webrtc.org/experiments/rtp-hdrext/abs-send-time\r\na=extmap:4 urn:3gpp:video-orientation\r\na=sendrecv\r\na=rtcp-mux\r\na=rtpmap:100 VP8/90000\r\na=rtcp-fb:100 ccm fir\r\na=rtcp-fb:100 nack\r\na=rtcp-fb:100 nack pli\r\na=rtcp-fb:100 goog-remb\r\na=rtpmap:116 red/90000\r\na=rtpmap:117 ulpfec/90000\r\na=rtpmap:96 rtx/90000\r\na=fmtp:96 apt=100\r\na=ssrc-group:FID 4237941122 4172266375\r\na=ssrc:4237941122 cname:HXv7l5kKsobv+2Ry\r\na=ssrc:4237941122 msid:ARDAMS ARDAMSv0\r\na=ssrc:4237941122 mslabel:ARDAMS\r\na=ssrc:4237941122 label:ARDAMSv0\r\na=ssrc:4172266375 cname:HXv7l5kKsobv+2Ry\r\na=ssrc:4172266375 msid:ARDAMS ARDAMSv0\r\na=ssrc:4172266375 mslabel:ARDAMS\r\na=ssrc:4172266375 label:ARDAMSv0\r\n");
//        System.out.println("v=0\r\no=- 9213924445711532423 2 IN IP4 127.0.0.1\r\ns=-\r\nt=0 0\r\na=group:BUNDLE audio video\r\na=msid-semantic: WMS yVdceZtNa2pd5DE9odIpTHOAUdyixzSml7yd\r\nm=audio 9 UDP/TLS/RTP/SAVPF 111 103 9 0 8 106 105 13 126\r\nc=IN IP4 0.0.0.0\r\na=rtcp:9 IN IP4 0.0.0.0\r\na=ice-ufrag:cCvS\r\na=ice-pwd:80zufj51EAwCv+3mOFo2hauR\r\na=ice-options:trickle\r\na=fingerprint:sha-256 D3:EC:6D:48:1B:4B:10:CD:46:66:0D:A2:37:13:49:41:C4:A6:C5:D7:C7:B2:5A:6A:DA:4A:9A:6B:92:F4:C2:E8\r\na=setup:active\r\na=mid:audio\r\na=extmap:1 urn:ietf:params:rtp-hdrext:ssrc-audio-level\r\na=sendrecv\r\na=rtcp-mux\r\na=rtpmap:111 opus/48000/2\r\na=fmtp:111 minptime=10;useinbandfec=1\r\na=rtpmap:103 ISAC/16000\r\na=rtpmap:9 G722/8000\r\na=rtpmap:0 PCMU/8000\r\na=rtpmap:8 PCMA/8000\r\na=rtpmap:106 CN/32000\r\na=rtpmap:105 CN/16000\r\na=rtpmap:13 CN/8000\r\na=rtpmap:126 telephone-event/8000\r\na=ssrc:1264137206 cname:dp8m04ani9Xz4BuL\r\na=ssrc:1264137206 msid:yVdceZtNa2pd5DE9odIpTHOAUdyixzSml7yd 3e7d0e3a-e5f5-4af7-8683-838745da2d76\r\na=ssrc:1264137206 mslabel:yVdceZtNa2pd5DE9odIpTHOAUdyixzSml7yd\r\na=ssrc:1264137206 label:3e7d0e3a-e5f5-4af7-8683-838745da2d76\r\nm=video 9 UDP/TLS/RTP/SAVPF 100 116 117 96\r\nc=IN IP4 0.0.0.0\r\na=rtcp:9 IN IP4 0.0.0.0\r\na=ice-ufrag:cCvS\r\na=ice-pwd:80zufj51EAwCv+3mOFo2hauR\r\na=ice-options:trickle\r\na=fingerprint:sha-256 D3:EC:6D:48:1B:4B:10:CD:46:66:0D:A2:37:13:49:41:C4:A6:C5:D7:C7:B2:5A:6A:DA:4A:9A:6B:92:F4:C2:E8\r\na=setup:active\r\na=mid:video\r\na=extmap:2 urn:ietf:params:rtp-hdrext:toffset\r\na=extmap:3 http://www.webrtc.org/experiments/rtp-hdrext/abs-send-time\r\na=extmap:4 urn:3gpp:video-orientation\r\na=sendrecv\r\na=rtcp-mux\r\na=rtpmap:100 VP8/90000\r\na=rtcp-fb:100 goog-remb\r\na=rtcp-fb:100 ccm fir\r\na=rtcp-fb:100 nack\r\na=rtcp-fb:100 nack pli\r\na=rtpmap:116 red/90000\r\na=rtpmap:117 ulpfec/90000\r\na=rtpmap:96 rtx/90000\r\na=fmtp:96 apt=100\r\na=ssrc-group:FID 1441734558 2226000327\r\na=ssrc:1441734558 cname:dp8m04ani9Xz4BuL\r\na=ssrc:1441734558 msid:yVdceZtNa2pd5DE9odIpTHOAUdyixzSml7yd f6c096a6-07f9-4d98-bea5-86e9f1481d70\r\na=ssrc:1441734558 mslabel:yVdceZtNa2pd5DE9odIpTHOAUdyixzSml7yd\r\na=ssrc:1441734558 label:f6c096a6-07f9-4d98-bea5-86e9f1481d70\r\na=ssrc:2226000327 cname:dp8m04ani9Xz4BuL\r\na=ssrc:2226000327 msid:yVdceZtNa2pd5DE9odIpTHOAUdyixzSml7yd f6c096a6-07f9-4d98-bea5-86e9f1481d70\r\na=ssrc:2226000327 mslabel:yVdceZtNa2pd5DE9odIpTHOAUdyixzSml7yd\r\na=ssrc:2226000327 label:f6c096a6-07f9-4d98-bea5-86e9f1481d70\r\n");
//        System.out.println("v=0\r\no=- 2789400766266533688 2 IN IP4 127.0.0.1\r\ns=-\r\nt=0 0\r\na=group:BUNDLE audio video\r\na=msid-semantic: WMS ARDAMS\r\nm=audio 9 UDP/TLS/RTP/SAVPF 111 103 9 102 0 8 106 105 13 127 126\r\nc=IN IP4 0.0.0.0\r\na=rtcp:9 IN IP4 0.0.0.0\r\na=ice-ufrag:gA0qqAZ51iS186S6\r\na=ice-pwd:OKt0TaypJhaPZ4DiSuaRDX4o\r\na=fingerprint:sha-256 60:F2:E3:60:5E:25:33:64:C4:98:B6:A8:86:86:64:A5:95:20:03:E2:52:5F:54:74:9E:74:36:DB:EA:36:14:F3\r\na=setup:actpass\r\na=mid:audio\r\na=extmap:1 urn:ietf:params:rtp-hdrext:ssrc-audio-level\r\na=extmap:3 http://www.webrtc.org/experiments/rtp-hdrext/abs-send-time\r\na=sendrecv\r\na=rtcp-mux\r\na=rtpmap:111 opus/48000/2\r\na=fmtp:111 minptime=10; useinbandfec=1\r\na=rtpmap:103 ISAC/16000\r\na=rtpmap:9 G722/8000\r\na=rtpmap:102 ILBC/8000\r\na=rtpmap:0 PCMU/8000\r\na=rtpmap:8 PCMA/8000\r\na=rtpmap:106 CN/32000\r\na=rtpmap:105 CN/16000\r\na=rtpmap:13 CN/8000\r\na=rtpmap:127 red/8000\r\na=rtpmap:126 telephone-event/8000\r\na=maxptime:60\r\na=ssrc:665797672 cname:iYpuQY9PXHojyXYb\r\na=ssrc:665797672 msid:ARDAMS ARDAMSa0\r\na=ssrc:665797672 mslabel:ARDAMS\r\na=ssrc:665797672 label:ARDAMSa0\r\nm=video 9 UDP/TLS/RTP/SAVPF 100 116 117 96\r\nc=IN IP4 0.0.0.0\r\na=rtcp:9 IN IP4 0.0.0.0\r\na=ice-ufrag:gA0qqAZ51iS186S6\r\na=ice-pwd:OKt0TaypJhaPZ4DiSuaRDX4o\r\na=fingerprint:sha-256 60:F2:E3:60:5E:25:33:64:C4:98:B6:A8:86:86:64:A5:95:20:03:E2:52:5F:54:74:9E:74:36:DB:EA:36:14:F3\r\na=setup:actpass\r\na=mid:video\r\na=extmap:2 urn:ietf:params:rtp-hdrext:toffset\r\na=extmap:3 http://www.webrtc.org/experiments/rtp-hdrext/abs-send-time\r\na=extmap:4 urn:3gpp:video-orientation\r\na=sendrecv\r\na=rtcp-mux\r\na=rtpmap:100 VP8/90000\r\na=rtcp-fb:100 ccm fir\r\na=rtcp-fb:100 nack\r\na=rtcp-fb:100 nack pli\r\na=rtcp-fb:100 goog-remb\r\na=rtpmap:116 red/90000\r\na=rtpmap:117 ulpfec/90000\r\na=rtpmap:96 rtx/90000\r\na=fmtp:96 apt=100\r\na=ssrc-group:FID 2687998196 919308025\r\na=ssrc:2687998196 cname:iYpuQY9PXHojyXYb\r\na=ssrc:2687998196 msid:ARDAMS ARDAMSv0\r\na=ssrc:2687998196 mslabel:ARDAMS\r\na=ssrc:2687998196 label:ARDAMSv0\r\na=ssrc:919308025 cname:iYpuQY9PXHojyXYb\r\na=ssrc:919308025 msid:ARDAMS ARDAMSv0\r\na=ssrc:919308025 mslabel:ARDAMS\r\na=ssrc:919308025 label:ARDAMSv0\r\n");
//        System.out.println("v=0\r\no=- 2285979840566782967 2 IN IP4 127.0.0.1\r\ns=-\r\nt=0 0\r\na=group:BUNDLE audio video\r\na=msid-semantic: WMS iHiePL0CHDqYfxEGFP2OGK90fgbyHyTlfo15\r\nm=audio 9 UDP/TLS/RTP/SAVPF 111 103 9 0 8 106 105 13 126\r\nc=IN IP4 0.0.0.0\r\na=rtcp:9 IN IP4 0.0.0.0\r\na=ice-ufrag:P3ot\r\na=ice-pwd:W7J4gAcB5BmcNliSsUnCrX8E\r\na=ice-options:trickle\r\na=fingerprint:sha-256 1C:18:DA:99:9A:B0:57:1C:40:D3:06:9C:03:4E:12:F3:BC:E1:2F:86:52:28:CE:B6:EF:BF:92:D5:39:F2:C3:44\r\na=setup:active\r\na=mid:audio\r\na=extmap:1 urn:ietf:params:rtp-hdrext:ssrc-audio-level\r\na=sendrecv\r\na=rtcp-mux\r\na=rtpmap:111 opus/48000/2\r\na=fmtp:111 minptime=10;useinbandfec=1\r\na=rtpmap:103 ISAC/16000\r\na=rtpmap:9 G722/8000\r\na=rtpmap:0 PCMU/8000\r\na=rtpmap:8 PCMA/8000\r\na=rtpmap:106 CN/32000\r\na=rtpmap:105 CN/16000\r\na=rtpmap:13 CN/8000\r\na=rtpmap:126 telephone-event/8000\r\na=ssrc:2561713026 cname:VphqV4rdehxVHpnA\r\na=ssrc:2561713026 msid:iHiePL0CHDqYfxEGFP2OGK90fgbyHyTlfo15 0406d954-5f1d-4322-b4a7-40d1154d6f36\r\na=ssrc:2561713026 mslabel:iHiePL0CHDqYfxEGFP2OGK90fgbyHyTlfo15\r\na=ssrc:2561713026 label:0406d954-5f1d-4322-b4a7-40d1154d6f36\r\nm=video 9 UDP/TLS/RTP/SAVPF 100 116 117 96\r\nc=IN IP4 0.0.0.0\r\na=rtcp:9 IN IP4 0.0.0.0\r\na=ice-ufrag:P3ot\r\na=ice-pwd:W7J4gAcB5BmcNliSsUnCrX8E\r\na=ice-options:trickle\r\na=fingerprint:sha-256 1C:18:DA:99:9A:B0:57:1C:40:D3:06:9C:03:4E:12:F3:BC:E1:2F:86:52:28:CE:B6:EF:BF:92:D5:39:F2:C3:44\r\na=setup:active\r\na=mid:video\r\na=extmap:2 urn:ietf:params:rtp-hdrext:toffset\r\na=extmap:3 http://www.webrtc.org/experiments/rtp-hdrext/abs-send-time\r\na=extmap:4 urn:3gpp:video-orientation\r\na=sendrecv\r\na=rtcp-mux\r\na=rtpmap:100 VP8/90000\r\na=rtcp-fb:100 goog-remb\r\na=rtcp-fb:100 ccm fir\r\na=rtcp-fb:100 nack\r\na=rtcp-fb:100 nack pli\r\na=rtpmap:116 red/90000\r\na=rtpmap:117 ulpfec/90000\r\na=rtpmap:96 rtx/90000\r\na=fmtp:96 apt=100\r\na=ssrc-group:FID 990903235 1639869189\r\na=ssrc:990903235 cname:VphqV4rdehxVHpnA\r\na=ssrc:990903235 msid:iHiePL0CHDqYfxEGFP2OGK90fgbyHyTlfo15 6dc87436-5d70-4494-8af0-697d8a135da5\r\na=ssrc:990903235 mslabel:iHiePL0CHDqYfxEGFP2OGK90fgbyHyTlfo15\r\na=ssrc:990903235 label:6dc87436-5d70-4494-8af0-697d8a135da5\r\na=ssrc:1639869189 cname:VphqV4rdehxVHpnA\r\na=ssrc:1639869189 msid:iHiePL0CHDqYfxEGFP2OGK90fgbyHyTlfo15 6dc87436-5d70-4494-8af0-697d8a135da5\r\na=ssrc:1639869189 mslabel:iHiePL0CHDqYfxEGFP2OGK90fgbyHyTlfo15\r\na=ssrc:1639869189 label:6dc87436-5d70-4494-8af0-697d8a135da5\r\n");
        System.out.println("v=0\r\no=- 3004620940240637497 2 IN IP4 127.0.0.1\r\ns=-\r\nt=0 0\r\na=group:BUNDLE audio video data\r\na=msid-semantic: WMS aBs6116KmoaIjLTa9VyQCbTqraLqIjXRsWRD\r\nm=audio 9 UDP/TLS/RTP/SAVPF 111 103 104 9 0 8 106 105 13 110 112 113 126\r\nc=IN IP4 0.0.0.0\r\na=rtcp:9 IN IP4 0.0.0.0\r\na=ice-ufrag:8wLC\r\na=ice-pwd:oW4KlBvypS5GPdR0UA7wmOF2\r\na=ice-options:trickle\r\na=fingerprint:sha-256 A1:12:21:E9:62:2A:DE:6C:16:42:AD:F1:A0:EB:C0:C5:D6:B8:87:CC:6F:4E:26:68:09:34:C3:72:C6:65:3B:4F\r\na=setup:active\r\na=mid:audio\r\na=extmap:1 urn:ietf:params:rtp-hdrext:ssrc-audio-level\r\na=sendrecv\r\na=rtcp-mux\r\na=rtpmap:111 opus/48000/2\r\na=rtcp-fb:111 transport-cc\r\na=fmtp:111 minptime=10;useinbandfec=1\r\na=rtpmap:103 ISAC/16000\r\na=rtpmap:104 ISAC/32000\r\na=rtpmap:9 G722/8000\r\na=rtpmap:0 PCMU/8000\r\na=rtpmap:8 PCMA/8000\r\na=rtpmap:106 CN/32000\r\na=rtpmap:105 CN/16000\r\na=rtpmap:13 CN/8000\r\na=rtpmap:110 telephone-event/48000\r\na=rtpmap:112 telephone-event/32000\r\na=rtpmap:113 telephone-event/16000\r\na=rtpmap:126 telephone-event/8000\r\na=ssrc:1034332006 cname:CnKIY940EfaadXO7\r\na=ssrc:1034332006 msid:aBs6116KmoaIjLTa9VyQCbTqraLqIjXRsWRD 038cabaa-670c-4ac6-9c46-ff922c13bb20\r\na=ssrc:1034332006 mslabel:aBs6116KmoaIjLTa9VyQCbTqraLqIjXRsWRD\r\na=ssrc:1034332006 label:038cabaa-670c-4ac6-9c46-ff922c13bb20\r\nm=video 9 UDP/TLS/RTP/SAVPF 96 97 98 99 100 101 102 123 127 122 125 107 108 109 124\r\nc=IN IP4 0.0.0.0\r\na=rtcp:9 IN IP4 0.0.0.0\r\na=ice-ufrag:8wLC\r\na=ice-pwd:oW4KlBvypS5GPdR0UA7wmOF2\r\na=ice-options:trickle\r\na=fingerprint:sha-256 A1:12:21:E9:62:2A:DE:6C:16:42:AD:F1:A0:EB:C0:C5:D6:B8:87:CC:6F:4E:26:68:09:34:C3:72:C6:65:3B:4F\r\na=setup:active\r\na=mid:video\r\na=extmap:2 urn:ietf:params:rtp-hdrext:toffset\r\na=extmap:3 http://www.webrtc.org/experiments/rtp-hdrext/abs-send-time\r\na=extmap:4 urn:3gpp:video-orientation\r\na=extmap:5 http://www.ietf.org/id/draft-holmer-rmcat-transport-wide-cc-extensions-01\r\na=extmap:6 http://www.webrtc.org/experiments/rtp-hdrext/playout-delay\r\na=extmap:7 http://www.webrtc.org/experiments/rtp-hdrext/video-content-type\r\na=extmap:8 http://www.webrtc.org/experiments/rtp-hdrext/video-timing\r\na=sendrecv\r\na=rtcp-mux\r\na=rtcp-rsize\r\na=rtpmap:96 VP8/90000\r\na=rtcp-fb:96 goog-remb\r\na=rtcp-fb:96 transport-cc\r\na=rtcp-fb:96 ccm fir\r\na=rtcp-fb:96 nack\r\na=rtcp-fb:96 nack pli\r\na=rtpmap:97 rtx/90000\r\na=fmtp:97 apt=96\r\na=rtpmap:98 VP9/90000\r\na=rtcp-fb:98 goog-remb\r\na=rtcp-fb:98 transport-cc\r\na=rtcp-fb:98 ccm fir\r\na=rtcp-fb:98 nack\r\na=rtcp-fb:98 nack pli\r\na=rtpmap:99 rtx/90000\r\na=fmtp:99 apt=98\r\na=rtpmap:100 H264/90000\r\na=rtcp-fb:100 goog-remb\r\na=rtcp-fb:100 transport-cc\r\na=rtcp-fb:100 ccm fir\r\na=rtcp-fb:100 nack\r\na=rtcp-fb:100 nack pli\r\na=fmtp:100 level-asymmetry-allowed=1;packetization-mode=1;profile-level-id=42001f\r\na=rtpmap:101 rtx/90000\r\na=fmtp:101 apt=100\r\na=rtpmap:102 H264/90000\r\na=rtcp-fb:102 goog-remb\r\na=rtcp-fb:102 transport-cc\r\na=rtcp-fb:102 ccm fir\r\na=rtcp-fb:102 nack\r\na=rtcp-fb:102 nack pli\r\na=fmtp:102 level-asymmetry-allowed=1;packetization-mode=1;profile-level-id=42e01f\r\na=rtpmap:123 rtx/90000\r\na=fmtp:123 apt=102\r\na=rtpmap:127 H264/90000\r\na=rtcp-fb:127 goog-remb\r\na=rtcp-fb:127 transport-cc\r\na=rtcp-fb:127 ccm fir\r\na=rtcp-fb:127 nack\r\na=rtcp-fb:127 nack pli\r\na=fmtp:127 level-asymmetry-allowed=1;packetization-mode=1;profile-level-id=4d0032\r\na=rtpmap:122 rtx/90000\r\na=fmtp:122 apt=127\r\na=rtpmap:125 H264/90000\r\na=rtcp-fb:125 goog-remb\r\na=rtcp-fb:125 transport-cc\r\na=rtcp-fb:125 ccm fir\r\na=rtcp-fb:125 nack\r\na=rtcp-fb:125 nack pli\r\na=fmtp:125 level-asymmetry-allowed=1;packetization-mode=1;profile-level-id=640032\r\na=rtpmap:107 rtx/90000\r\na=fmtp:107 apt=125\r\na=rtpmap:108 red/90000\r\na=rtpmap:109 rtx/90000\r\na=fmtp:109 apt=108\r\na=rtpmap:124 ulpfec/90000\r\na=ssrc-group:FID 3769518336 2167471583\r\na=ssrc:3769518336 cname:CnKIY940EfaadXO7\r\na=ssrc:3769518336 msid:aBs6116KmoaIjLTa9VyQCbTqraLqIjXRsWRD b4dfc174-fb2b-43fe-bcaf-6594a043c7f6\r\na=ssrc:3769518336 mslabel:aBs6116KmoaIjLTa9VyQCbTqraLqIjXRsWRD\r\na=ssrc:3769518336 label:b4dfc174-fb2b-43fe-bcaf-6594a043c7f6\r\na=ssrc:2167471583 cname:CnKIY940EfaadXO7\r\na=ssrc:2167471583 msid:aBs6116KmoaIjLTa9VyQCbTqraLqIjXRsWRD b4dfc174-fb2b-43fe-bcaf-6594a043c7f6\r\na=ssrc:2167471583 mslabel:aBs6116KmoaIjLTa9VyQCbTqraLqIjXRsWRD\r\na=ssrc:2167471583 label:b4dfc174-fb2b-43fe-bcaf-6594a043c7f6\r\nm=application 9 DTLS/SCTP 5000\r\nc=IN IP4 0.0.0.0\r\nb=AS:30\r\na=ice-ufrag:8wLC\r\na=ice-pwd:oW4KlBvypS5GPdR0UA7wmOF2\r\na=ice-options:trickle\r\na=fingerprint:sha-256 A1:12:21:E9:62:2A:DE:6C:16:42:AD:F1:A0:EB:C0:C5:D6:B8:87:CC:6F:4E:26:68:09:34:C3:72:C6:65:3B:4F\r\na=setup:active\r\na=mid:data\r\na=sctpmap:5000 webrtc-datachannel 1024\r\n");
    }
}
