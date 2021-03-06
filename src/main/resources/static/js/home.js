const countries = [
    "Andorra",
    "United Arab Emirates",
    "Afghanistan",
    "Antigua and Barbuda",
    "Anguilla",
    "Albania",
    "Armenia",
    "Angola",
    "Antarctica",
    "Argentina",
    "American Samoa",
    "Austria",
    "Australia",
    "Aruba",
    "Aland Islands",
    "Azerbaijan",
    "Bosnia and Herzegovina",
    "Barbados",
    "Bangladesh",
    "Belgium",
    "Burkina Faso",
    "Bulgaria",
    "Bahrain",
    "Burundi",
    "Benin",
    "Saint Barthelemy",
    "Bermuda",
    "Brunei",
    "Bolivia",
    "Caribbean Netherlands",
    "Brazil",
    "Bahamas",
    "Bhutan",
    "Bouvet Island",
    "Botswana",
    "Belarus",
    "Belize",
    "Canada",
    "Cocos Islands",
    "DR Congo",
    "Central African Republic",
    "Republic of the Congo",
    "Switzerland",
    "Cote Ivoire",
    "Cook Islands",
    "Chile",
    "Cameroon",
    "China",
    "Colombia",
    "Costa Rica",
    "Cuba",
    "Cape Verde",
    "Curacao",
    "Christmas Island",
    "Cyprus",
    "Czechia",
    "Germany",
    "Djibouti",
    "Denmark",
    "Dominica",
    "Dominican Republic",
    "Algeria",
    "Ecuador",
    "Estonia",
    "Egypt",
    "Western Sahara",
    "Eritrea",
    "Spain",
    "Ethiopia",
    "European Union",
    "Finland",
    "Fiji",
    "Falkland Islands",
    "Micronesia",
    "Faroe Islands",
    "France",
    "Gabon",
    "United Kingdom",
    "Grenada",
    "Georgia",
    "French Guiana",
    "Guernsey",
    "Ghana",
    "Gibraltar",
    "Greenland",
    "Gambia",
    "Guinea",
    "Guadeloupe",
    "Equatorial Guinea",
    "Greece",
    "South Georgia",
    "Guatemala",
    "Guam",
    "Guinea-Bissau",
    "Guyana",
    "Hong Kong",
    "Heard Island and McDonald Islands",
    "Honduras",
    "Croatia",
    "Haiti",
    "Hungary",
    "Indonesia",
    "Ireland",
    "Israel",
    "Isle of Man",
    "India",
    "British Indian Ocean Territory",
    "Iraq",
    "Iran",
    "Iceland",
    "Italy",
    "Jersey",
    "Jamaica",
    "Jordan",
    "Japan",
    "Kenya",
    "Kyrgyzstan",
    "Cambodia",
    "Kiribati",
    "Comoros",
    "Saint Kitts and Nevis",
    "North Korea",
    "South Korea",
    "Kuwait",
    "Cayman Islands",
    "Kazakhstan",
    "Laos",
    "Lebanon",
    "Saint Lucia",
    "Liechtenstein",
    "Sri Lanka",
    "Liberia",
    "Lesotho",
    "Lithuania",
    "Luxembourg",
    "Latvia",
    "Libya",
    "Morocco",
    "Monaco",
    "Moldova",
    "Montenegro",
    "Saint Martin",
    "Madagascar",
    "Marshall Islands",
    "North Macedonia",
    "Mali",
    "Myanmar",
    "Mongolia",
    "Macau",
    "Northern Mariana Islands",
    "Martinique",
    "Mauritania",
    "Montserrat",
    "Malta",
    "Mauritius",
    "Maldives",
    "Malawi",
    "Mexico",
    "Malaysia",
    "Mozambique",
    "Namibia",
    "New Caledonia",
    "Niger",
    "Norfolk Island",
    "Nigeria",
    "Nicaragua",
    "Netherlands",
    "Norway",
    "Nepal",
    "Nauru",
    "Niue",
    "New Zealand",
    "Oman",
    "Panama",
    "Peru",
    "French Polynesia",
    "Papua New Guinea",
    "Philippines",
    "Pakistan",
    "Poland",
    "Saint Pierre and Miquelon",
    "Pitcairn Islands",
    "Puerto Rico",
    "Palestine",
    "Portugal",
    "Palau",
    "Paraguay",
    "Qatar",
    "Reunion",
    "Romania",
    "Serbia",
    "Russia",
    "Rwanda",
    "Saudi Arabia",
    "Solomon Islands",
    "Seychelles",
    "Sudan",
    "Sweden",
    "Singapore",
    "Saint Helena Ascension and Tristan da Cunha",
    "Slovenia",
    "Svalbard and Jan Mayen",
    "Slovakia",
    "Sierra Leone",
    "San Marino",
    "Senegal",
    "Somalia",
    "Suriname",
    "South Sudan",
    "Sao Tome and Principe",
    "El Salvador",
    "Sint Maarten",
    "Syria",
    "Eswatini",
    "Turks and Caicos Islands",
    "Chad",
    "French Southern and Antarctic Lands",
    "Togo",
    "Thailand",
    "Tajikistan",
    "Tokelau",
    "Timor-Leste",
    "Turkmenistan",
    "Tunisia",
    "Tonga",
    "Turkey",
    "Trinidad and Tobago",
    "Tuvalu",
    "Taiwan",
    "Tanzania",
    "Ukraine",
    "Uganda",
    "United States Minor Outlying Islands",
    "United Nations",
    "United States",
    "Uruguay",
    "Uzbekistan",
    "Vatican City",
    "Saint Vincent and the Grenadines",
    "Venezuela",
    "British Virgin Islands",
    "United States Virgin Islands",
    "Vietnam",
    "Vanuatu",
    "Wallis and Futuna",
    "Samoa",
    "Kosovo",
    "Yemen",
    "Mayotte",
    "South Africa",
    "Zambia",
    "Zimbabwe",
],
flags = [
    "https://flagcdn.com/32x24/ad.png",
    "https://flagcdn.com/32x24/ae.png",
    "https://flagcdn.com/32x24/af.png",
    "https://flagcdn.com/32x24/ag.png",
    "https://flagcdn.com/32x24/ai.png",
    "https://flagcdn.com/32x24/al.png",
    "https://flagcdn.com/32x24/am.png",
    "https://flagcdn.com/32x24/ao.png",
    "https://flagcdn.com/32x24/aq.png",
    "https://flagcdn.com/32x24/ar.png",
    "https://flagcdn.com/32x24/as.png",
    "https://flagcdn.com/32x24/at.png",
    "https://flagcdn.com/32x24/au.png",
    "https://flagcdn.com/32x24/aw.png",
    "https://flagcdn.com/32x24/ax.png",
    "https://flagcdn.com/32x24/az.png",
    "https://flagcdn.com/32x24/ba.png",
    "https://flagcdn.com/32x24/bb.png",
    "https://flagcdn.com/32x24/bd.png",
    "https://flagcdn.com/32x24/be.png",
    "https://flagcdn.com/32x24/bf.png",
    "https://flagcdn.com/32x24/bg.png",
    "https://flagcdn.com/32x24/bh.png",
    "https://flagcdn.com/32x24/bi.png",
    "https://flagcdn.com/32x24/bj.png",
    "https://flagcdn.com/32x24/bl.png",
    "https://flagcdn.com/32x24/bm.png",
    "https://flagcdn.com/32x24/bn.png",
    "https://flagcdn.com/32x24/bo.png",
    "https://flagcdn.com/32x24/bq.png",
    "https://flagcdn.com/32x24/br.png",
    "https://flagcdn.com/32x24/bs.png",
    "https://flagcdn.com/32x24/bt.png",
    "https://flagcdn.com/32x24/bv.png",
    "https://flagcdn.com/32x24/bw.png",
    "https://flagcdn.com/32x24/by.png",
    "https://flagcdn.com/32x24/bz.png",
    "https://flagcdn.com/32x24/ca.png",
    "https://flagcdn.com/32x24/cc.png",
    "https://flagcdn.com/32x24/cd.png",
    "https://flagcdn.com/32x24/cf.png",
    "https://flagcdn.com/32x24/cg.png",
    "https://flagcdn.com/32x24/ch.png",
    "https://flagcdn.com/32x24/ci.png",
    "https://flagcdn.com/32x24/ck.png",
    "https://flagcdn.com/32x24/cl.png",
    "https://flagcdn.com/32x24/cm.png",
    "https://flagcdn.com/32x24/cn.png",
    "https://flagcdn.com/32x24/co.png",
    "https://flagcdn.com/32x24/cr.png",
    "https://flagcdn.com/32x24/cu.png",
    "https://flagcdn.com/32x24/cv.png",
    "https://flagcdn.com/32x24/cw.png",
    "https://flagcdn.com/32x24/cx.png",
    "https://flagcdn.com/32x24/cy.png",
    "https://flagcdn.com/32x24/cz.png",
    "https://flagcdn.com/32x24/de.png",
    "https://flagcdn.com/32x24/dj.png",
    "https://flagcdn.com/32x24/dk.png",
    "https://flagcdn.com/32x24/dm.png",
    "https://flagcdn.com/32x24/do.png",
    "https://flagcdn.com/32x24/dz.png",
    "https://flagcdn.com/32x24/ec.png",
    "https://flagcdn.com/32x24/ee.png",
    "https://flagcdn.com/32x24/eg.png",
    "https://flagcdn.com/32x24/eh.png",
    "https://flagcdn.com/32x24/er.png",
    "https://flagcdn.com/32x24/es.png",
    "https://flagcdn.com/32x24/et.png",
    "https://flagcdn.com/32x24/eu.png",
    "https://flagcdn.com/32x24/fi.png",
    "https://flagcdn.com/32x24/fj.png",
    "https://flagcdn.com/32x24/fk.png",
    "https://flagcdn.com/32x24/fm.png",
    "https://flagcdn.com/32x24/fo.png",
    "https://flagcdn.com/32x24/fr.png",
    "https://flagcdn.com/32x24/ga.png",
    "https://flagcdn.com/32x24/gb.png",
    "https://flagcdn.com/32x24/gd.png",
    "https://flagcdn.com/32x24/ge.png",
    "https://flagcdn.com/32x24/gf.png",
    "https://flagcdn.com/32x24/gg.png",
    "https://flagcdn.com/32x24/gh.png",
    "https://flagcdn.com/32x24/gi.png",
    "https://flagcdn.com/32x24/gl.png",
    "https://flagcdn.com/32x24/gm.png",
    "https://flagcdn.com/32x24/gn.png",
    "https://flagcdn.com/32x24/gp.png",
    "https://flagcdn.com/32x24/gq.png",
    "https://flagcdn.com/32x24/gr.png",
    "https://flagcdn.com/32x24/gs.png",
    "https://flagcdn.com/32x24/gt.png",
    "https://flagcdn.com/32x24/gu.png",
    "https://flagcdn.com/32x24/gw.png",
    "https://flagcdn.com/32x24/gy.png",
    "https://flagcdn.com/32x24/hk.png",
    "https://flagcdn.com/32x24/hm.png",
    "https://flagcdn.com/32x24/hn.png",
    "https://flagcdn.com/32x24/hr.png",
    "https://flagcdn.com/32x24/ht.png",
    "https://flagcdn.com/32x24/hu.png",
    "https://flagcdn.com/32x24/id.png",
    "https://flagcdn.com/32x24/ie.png",
    "https://flagcdn.com/32x24/il.png",
    "https://flagcdn.com/32x24/im.png",
    "https://flagcdn.com/32x24/in.png",
    "https://flagcdn.com/32x24/io.png",
    "https://flagcdn.com/32x24/iq.png",
    "https://flagcdn.com/32x24/ir.png",
    "https://flagcdn.com/32x24/is.png",
    "https://flagcdn.com/32x24/it.png",
    "https://flagcdn.com/32x24/je.png",
    "https://flagcdn.com/32x24/jm.png",
    "https://flagcdn.com/32x24/jo.png",
    "https://flagcdn.com/32x24/jp.png",
    "https://flagcdn.com/32x24/ke.png",
    "https://flagcdn.com/32x24/kg.png",
    "https://flagcdn.com/32x24/kh.png",
    "https://flagcdn.com/32x24/ki.png",
    "https://flagcdn.com/32x24/km.png",
    "https://flagcdn.com/32x24/kn.png",
    "https://flagcdn.com/32x24/kp.png",
    "https://flagcdn.com/32x24/kr.png",
    "https://flagcdn.com/32x24/kw.png",
    "https://flagcdn.com/32x24/ky.png",
    "https://flagcdn.com/32x24/kz.png",
    "https://flagcdn.com/32x24/la.png",
    "https://flagcdn.com/32x24/lb.png",
    "https://flagcdn.com/32x24/lc.png",
    "https://flagcdn.com/32x24/li.png",
    "https://flagcdn.com/32x24/lk.png",
    "https://flagcdn.com/32x24/lr.png",
    "https://flagcdn.com/32x24/ls.png",
    "https://flagcdn.com/32x24/lt.png",
    "https://flagcdn.com/32x24/lu.png",
    "https://flagcdn.com/32x24/lv.png",
    "https://flagcdn.com/32x24/ly.png",
    "https://flagcdn.com/32x24/ma.png",
    "https://flagcdn.com/32x24/mc.png",
    "https://flagcdn.com/32x24/md.png",
    "https://flagcdn.com/32x24/me.png",
    "https://flagcdn.com/32x24/mf.png",
    "https://flagcdn.com/32x24/mg.png",
    "https://flagcdn.com/32x24/mh.png",
    "https://flagcdn.com/32x24/mk.png",
    "https://flagcdn.com/32x24/ml.png",
    "https://flagcdn.com/32x24/mm.png",
    "https://flagcdn.com/32x24/mn.png",
    "https://flagcdn.com/32x24/mo.png",
    "https://flagcdn.com/32x24/mp.png",
    "https://flagcdn.com/32x24/mq.png",
    "https://flagcdn.com/32x24/mr.png",
    "https://flagcdn.com/32x24/ms.png",
    "https://flagcdn.com/32x24/mt.png",
    "https://flagcdn.com/32x24/mu.png",
    "https://flagcdn.com/32x24/mv.png",
    "https://flagcdn.com/32x24/mw.png",
    "https://flagcdn.com/32x24/mx.png",
    "https://flagcdn.com/32x24/my.png",
    "https://flagcdn.com/32x24/mz.png",
    "https://flagcdn.com/32x24/na.png",
    "https://flagcdn.com/32x24/nc.png",
    "https://flagcdn.com/32x24/ne.png",
    "https://flagcdn.com/32x24/nf.png",
    "https://flagcdn.com/32x24/ng.png",
    "https://flagcdn.com/32x24/ni.png",
    "https://flagcdn.com/32x24/nl.png",
    "https://flagcdn.com/32x24/no.png",
    "https://flagcdn.com/32x24/np.png",
    "https://flagcdn.com/32x24/nr.png",
    "https://flagcdn.com/32x24/nu.png",
    "https://flagcdn.com/32x24/nz.png",
    "https://flagcdn.com/32x24/om.png",
    "https://flagcdn.com/32x24/pa.png",
    "https://flagcdn.com/32x24/pe.png",
    "https://flagcdn.com/32x24/pf.png",
    "https://flagcdn.com/32x24/pg.png",
    "https://flagcdn.com/32x24/ph.png",
    "https://flagcdn.com/32x24/pk.png",
    "https://flagcdn.com/32x24/pl.png",
    "https://flagcdn.com/32x24/pm.png",
    "https://flagcdn.com/32x24/pn.png",
    "https://flagcdn.com/32x24/pr.png",
    "https://flagcdn.com/32x24/ps.png",
    "https://flagcdn.com/32x24/pt.png",
    "https://flagcdn.com/32x24/pw.png",
    "https://flagcdn.com/32x24/py.png",
    "https://flagcdn.com/32x24/qa.png",
    "https://flagcdn.com/32x24/.repng",
    "https://flagcdn.com/32x24/ro.png",
    "https://flagcdn.com/32x24/rs.png",
    "https://flagcdn.com/32x24/ru.png",
    "https://flagcdn.com/32x24/rw.png",
    "https://flagcdn.com/32x24/sa.png",
    "https://flagcdn.com/32x24/sb.png",
    "https://flagcdn.com/32x24/sc.png",
    "https://flagcdn.com/32x24/sd.png",
    "https://flagcdn.com/32x24/se.png",
    "https://flagcdn.com/32x24/sg.png",
    "https://flagcdn.com/32x24/sh.png",
    "https://flagcdn.com/32x24/si.png",
    "https://flagcdn.com/32x24/sj.png",
    "https://flagcdn.com/32x24/sk.png",
    "https://flagcdn.com/32x24/sl.png",
    "https://flagcdn.com/32x24/sm.png",
    "https://flagcdn.com/32x24/sn.png",
    "https://flagcdn.com/32x24/so.png",
    "https://flagcdn.com/32x24/sr.png",
    "https://flagcdn.com/32x24/ss.png",
    "https://flagcdn.com/32x24/st.png",
    "https://flagcdn.com/32x24/sv.png",
    "https://flagcdn.com/32x24/sx.png",
    "https://flagcdn.com/32x24/sy.png",
    "https://flagcdn.com/32x24/sz.png",
    "https://flagcdn.com/32x24/tc.png",
    "https://flagcdn.com/32x24/td.png",
    "https://flagcdn.com/32x24/tf.png",
    "https://flagcdn.com/32x24/tg.png",
    "https://flagcdn.com/32x24/th.png",
    "https://flagcdn.com/32x24/tj.png",
    "https://flagcdn.com/32x24/tk.png",
    "https://flagcdn.com/32x24/tl.png",
    "https://flagcdn.com/32x24/tm.png",
    "https://flagcdn.com/32x24/tn.png",
    "https://flagcdn.com/32x24/to.png",
    "https://flagcdn.com/32x24/tr.png",
    "https://flagcdn.com/32x24/tt.png",
    "https://flagcdn.com/32x24/tv.png",
    "https://flagcdn.com/32x24/tw.png",
    "https://flagcdn.com/32x24/tz.png",
    "https://flagcdn.com/32x24/ua.png",
    "https://flagcdn.com/32x24/ug.png",
    "https://flagcdn.com/32x24/um.png",
    "https://flagcdn.com/32x24/un.png",
    "https://flagcdn.com/32x24/us.png",
    "https://flagcdn.com/32x24/uy.png",
    "https://flagcdn.com/32x24/uz.png",
    "https://flagcdn.com/32x24/va.png",
    "https://flagcdn.com/32x24/vc.png",
    "https://flagcdn.com/32x24/ve.png",
    "https://flagcdn.com/32x24/vg.png",
    "https://flagcdn.com/32x24/vi.png",
    "https://flagcdn.com/32x24/vn.png",
    "https://flagcdn.com/32x24/vu.png",
    "https://flagcdn.com/32x24/wf.png",
    "https://flagcdn.com/32x24/ws.png",
    "https://flagcdn.com/32x24/xk.png",
    "https://flagcdn.com/32x24/ye.png",
    "https://flagcdn.com/32x24/yt.png",
    "https://flagcdn.com/32x24/za.png",
    "https://flagcdn.com/32x24/zm.png",
    "https://flagcdn.com/32x24/zw.png",
],
airlines = ["Aeroflot", "Air France", "El-Al", "Air Asia", "Air Malta", "Oman Air", "Wizzair"];
var username = "";
$(function () {
$.get("/getmyprincipal", function (n) {
    document.getElementById("username-div").innerHTML = "<img src='/images/user.png'/>&nbsp;&nbsp;<a href='#'>" + n.charAt(0).toUpperCase() + n.slice(1) + "</a>";
}),
    $("#spinner").show(),
    $.getJSON("/flights", function (n) {
        var t = n.sort(function (n, t) {
                return n.flightId - t.flightId;
            }),
            a = t.report;
        $("#flights-table")
            .find("thead")
            .append(
                '<tr><th scope="col"><i class="fa-solid fa-hashtag"></i>&nbsp;Flight No</th><th scope="col"><i class="fa-brands fa-confluence"></i>&nbsp;Airline</th><th scope="col"><i class="fa-solid fa-location-arrow"></i>&nbsp;Origin</th><th scope="col"><i class="fa-solid fa-location-arrow"></i>&nbsp;Destination</th><th scope="col"><i class="fa-solid fa-plane-departure"></i>&nbsp;Departure</th><th scope="col"><i class="fa-solid fa-plane-arrival"></i>&nbsp;Arrival</th><th scope="col"class="lastHeader"><i class="fa-solid fa-circle-question"></i>&nbsp;Info</th>'
            );
        for (var c = 0; c < t.length; c++) {
            a = c == t.length - 1 ? $("<tr class='lastRow'>") : $("<tr/>");
            var g = airlines[t[c].airlineCompanyId - 1],
                p = t[c].originCountryId,
                s = t[c].destinationCountryId,
                o = t[c].departureTime,
                l = t[c].landingTime;
            a.append("<td>" + t[c].flightId + "</td>"),
                a.append("<td>" + g + "</td>"),
                a.append("<td><img src='" + flags[p - 1] + "'/>&nbsp;&nbsp;" + countries[p - 1] + "</td>"),
                a.append("<td><img src='" + flags[s - 1] + "'/>&nbsp;&nbsp;" + countries[s - 1] + "</td>"),
                a.append("<td>" + o.substr(0, 10) + "&nbsp;&nbsp;" + o.substr(11, 5) + "</td>"),
                a.append("<td>" + l.substr(0, 10) + "&nbsp;&nbsp;" + l.substr(11, 5) + "</td>"),
                a.append('<td><button class="btn btn-dark btm-sm" type="button"><i class="fa-solid fa-cart-shopping"></i>&nbsp;&nbsp;Get tickets</button></td>'),
                $("#flights-table").find("tbody").append(a);
        }
    }).always(function () {
        $("#spinner").hide();
    }),
    $("#search").on("keyup", function () {
        $("#flights-table tr").length;
        var n = $(this).val().toLowerCase();
        $("table tbody tr").each(function () {
            ($row = $(this)), $row.find("td:nth-child(1)").text().toLowerCase().search(n) < 0 ? $row.hide() : $row.show();
        });
    });
});
