--
-- PostgreSQL database cluster dump
--

-- Started on 2025-04-24 21:27:23

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS;

--
-- User Configurations
--








--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.8
-- Dumped by pg_dump version 16.8

-- Started on 2025-04-24 21:27:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2025-04-24 21:27:23

--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.8
-- Dumped by pg_dump version 16.8

-- Started on 2025-04-24 21:27:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 4879 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


-- Completed on 2025-04-24 21:27:24

--
-- PostgreSQL database dump complete
--

--
-- Database "zitrone" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.8
-- Dumped by pg_dump version 16.8

-- Started on 2025-04-24 21:27:24

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5136 (class 1262 OID 16398)
-- Name: zitrone; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE zitrone WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'de-DE';


ALTER DATABASE zitrone OWNER TO postgres;

\connect zitrone

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16399)
-- Name: addresses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.addresses (
    ismain boolean NOT NULL,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    city character varying(255),
    country character varying(255),
    email character varying(255),
    firstname character varying(255),
    foto character varying(255),
    lastname character varying(255),
    phone character varying(255),
    postcode character varying(255),
    salutation character varying(255),
    signature character varying(255),
    street character varying(255),
    title character varying(255),
    type character varying(255),
    "`type`" character varying(255)
);


ALTER TABLE public.addresses OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16406)
-- Name: adults; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.adults (
    birdthyear integer,
    carerid bigint,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    tamagotchiid bigint,
    updatedat timestamp(6) without time zone,
    avatar character varying(255),
    email character varying(255),
    firstname character varying(255),
    lastname character varying(255),
    phone character varying(255),
    preferredlanguage character varying(255),
    relationship character varying(255),
    salutation character varying(255),
    carer_id bigint,
    carer bigint
);


ALTER TABLE public.adults OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16413)
-- Name: auditlogs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.auditlogs (
    createdat timestamp(6) without time zone,
    entityid bigint,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    action character varying(255),
    details text,
    entity character varying(255),
    level character varying(255),
    username character varying(255),
    "`action`" character varying(255),
    "`level`" character varying(255)
);


ALTER TABLE public.auditlogs OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16420)
-- Name: chatlogs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.chatlogs (
    adultid bigint,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    message text,
    sender character varying(255)
);


ALTER TABLE public.chatlogs OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16427)
-- Name: chatmessageentities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.chatmessageentities (
    type smallint,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    memoryid bigint,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    message text,
    "`type`" integer,
    CONSTRAINT chatmessageentities_type_check CHECK (((type >= 0) AND (type <= 4)))
);


ALTER TABLE public.chatmessageentities OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16435)
-- Name: countries; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.countries (
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    code character varying(255),
    region character varying(255)
);


ALTER TABLE public.countries OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16442)
-- Name: drugs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.drugs (
    refillfrom integer,
    strength integer,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    imagebox character varying(255),
    imagemedicament character varying(255),
    imprint character varying(255),
    name character varying(255),
    ndc9 character varying(255),
    primarycolor character varying(255),
    secundarycolor character varying(255),
    shape character varying(255),
    supplier character varying(255)
);


ALTER TABLE public.drugs OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16449)
-- Name: emojies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.emojies (
    level integer NOT NULL,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    emotion character varying(255),
    face character varying(255),
    name character varying(255),
    unicode character varying(255),
    "`level`" integer NOT NULL
);


ALTER TABLE public.emojies OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16456)
-- Name: endpoints; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.endpoints (
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    description character varying(255),
    target character varying(255)
);


ALTER TABLE public.endpoints OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16463)
-- Name: events; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.events (
    canbemissed boolean NOT NULL,
    remindercount integer,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    content text,
    note text,
    reminderinterval character varying(255),
    status character varying(255),
    title character varying(255),
    type character varying(255),
    color character varying(255),
    descritpion character varying(255),
    "`type`" character varying(255)
);


ALTER TABLE public.events OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16470)
-- Name: globalproperties; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.globalproperties (
    enableuserregistration boolean NOT NULL,
    forgotpassword boolean NOT NULL,
    maxfailattempts integer,
    minpasswortlength integer,
    confirmationcodeemail_id bigint,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    inviteusermail_id bigint,
    ownerid bigint,
    resetcredentialsmail_id bigint,
    updatedat timestamp(6) without time zone,
    accesstokelifespan character varying(255),
    appemail character varying(255),
    applogo character varying(255),
    appname character varying(255),
    deeplapikey character varying(255),
    deeplbaseurl character varying(255),
    defaultpassword character varying(255),
    exchangerateapikey character varying(255),
    exchangerateurl character varying(255),
    externaltotplifespan character varying(255),
    loginactiontimeout character varying(255),
    magiclinklifespan character varying(255)
);


ALTER TABLE public.globalproperties OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16483)
-- Name: hannilogs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hannilogs (
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    messageid character varying(255)
);


ALTER TABLE public.hannilogs OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16488)
-- Name: hannitasks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hannitasks (
    completed boolean NOT NULL,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    task character varying(255),
    type character varying(255),
    "`type`" character varying(255)
);


ALTER TABLE public.hannitasks OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16495)
-- Name: histories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.histories (
    height integer,
    weight integer,
    adultid bigint,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    mood character varying(255),
    note character varying(255),
    type character varying(255),
    "`type`" character varying(255)
);


ALTER TABLE public.histories OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16502)
-- Name: mailings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mailings (
    istemplate boolean NOT NULL,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    audience character varying(255),
    description character varying(255),
    mailingname character varying(255),
    message text,
    subject character varying(255),
    type character varying(255),
    "`type`" character varying(255)
);


ALTER TABLE public.mailings OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 16509)
-- Name: messagingprovider; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.messagingprovider (
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    accountname character varying(255),
    clientid character varying(255),
    clientidtest character varying(255),
    provider character varying(255),
    secret character varying(255),
    secrettest character varying(255)
);


ALTER TABLE public.messagingprovider OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16516)
-- Name: p2pgroup_members; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.p2pgroup_members (
    p2pgroup_id bigint NOT NULL,
    members bigint
);


ALTER TABLE public.p2pgroup_members OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 16519)
-- Name: p2pgroup_seenby; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.p2pgroup_seenby (
    p2pgroup_id bigint NOT NULL,
    seenby bigint
);


ALTER TABLE public.p2pgroup_seenby OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 16522)
-- Name: p2pgroups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.p2pgroups (
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    image character varying(255),
    lastmessage character varying(255),
    name character varying(255)
);


ALTER TABLE public.p2pgroups OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 16529)
-- Name: p2pmessage_seenby; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.p2pmessage_seenby (
    p2pmessage_id bigint NOT NULL,
    seenby bigint
);


ALTER TABLE public.p2pmessage_seenby OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 16532)
-- Name: p2pmessages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.p2pmessages (
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    reciverid bigint,
    senderid bigint,
    updatedat timestamp(6) without time zone,
    message text,
    senderimage character varying(255),
    sendername character varying(255)
);


ALTER TABLE public.p2pmessages OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 16539)
-- Name: prescription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prescription (
    quantity integer,
    adultid bigint,
    calendarwhen timestamp(6) without time zone,
    createdat timestamp(6) without time zone,
    drug_id bigint,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    calendarremind character varying(255),
    calendarrepeat character varying(255),
    contraindications character varying(255),
    cronstring character varying(255),
    description character varying(255),
    dosage character varying(255),
    indications character varying(255)
);


ALTER TABLE public.prescription OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 16548)
-- Name: queries; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.queries (
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    classname character varying(255),
    description character varying(255),
    partialwhere text
);


ALTER TABLE public.queries OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 16555)
-- Name: role_endpoints; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role_endpoints (
    role_id bigint NOT NULL,
    endpoints character varying(255)
);


ALTER TABLE public.role_endpoints OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 16558)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    istemplate boolean NOT NULL,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    description character varying(255),
    rolename character varying(255),
    selectionmethod character varying(255)
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 16714)
-- Name: tamagotchies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tamagotchies (
    id bigint NOT NULL,
    createdat timestamp(6) without time zone,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    age integer NOT NULL,
    alive boolean NOT NULL,
    avatar character varying(255),
    bedtime time(6) without time zone,
    cleanliness integer NOT NULL,
    currentemotion character varying(255),
    energy integer NOT NULL,
    experience integer NOT NULL,
    generalemotion integer NOT NULL,
    happiness integer NOT NULL,
    health integer NOT NULL,
    hunger integer NOT NULL,
    level integer NOT NULL,
    name character varying(255),
    sleeping boolean NOT NULL,
    weight integer NOT NULL
);


ALTER TABLE public.tamagotchies OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 16572)
-- Name: user_internalactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_internalactions (
    user_id bigint NOT NULL,
    internalactions character varying(255)
);


ALTER TABLE public.user_internalactions OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 16575)
-- Name: userpreferences; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.userpreferences (
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    ownerid bigint,
    updatedat timestamp(6) without time zone,
    classname character varying(255),
    view character varying(255),
    "`view`" character varying(255)
);


ALTER TABLE public.userpreferences OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 16582)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    failedattemptscount integer NOT NULL,
    isemailverified boolean NOT NULL,
    isenabled boolean NOT NULL,
    islive boolean NOT NULL,
    ismfaexempt boolean NOT NULL,
    istemporalpass boolean NOT NULL,
    istotpregistred boolean NOT NULL,
    rowsperpage integer NOT NULL,
    sessionid integer NOT NULL,
    createdat timestamp(6) without time zone,
    id bigint NOT NULL,
    lastsignin timestamp(6) without time zone,
    messagingprovider_id bigint,
    ownerid bigint,
    role_id bigint,
    updatedat timestamp(6) without time zone,
    avatar character varying(255),
    email character varying(255),
    firstname character varying(255),
    invitationrequesturl character varying(255),
    lastname character varying(255),
    password character varying(255),
    phone character varying(255),
    preferredlanguage character varying(255),
    preferredtheme character varying(255),
    status character varying(255),
    type character varying(255),
    username character varying(255),
    usersecret character varying(255),
    "`type`" character varying(255),
    account_id bigint,
    messagingproviderid bigint
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 16593)
-- Name: users_hannitasks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_hannitasks (
    user_id bigint NOT NULL,
    tasks_id bigint NOT NULL
);


ALTER TABLE public.users_hannitasks OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 16598)
-- Name: users_queries; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_queries (
    user_id bigint NOT NULL,
    queries_id bigint NOT NULL
);


ALTER TABLE public.users_queries OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 16603)
-- Name: users_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_roles (
    user_id bigint NOT NULL,
    associatedroles_id bigint NOT NULL
);


ALTER TABLE public.users_roles OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 16608)
-- Name: users_userpreferences; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_userpreferences (
    user_id bigint NOT NULL,
    preferences_id bigint NOT NULL
);


ALTER TABLE public.users_userpreferences OWNER TO postgres;

--
-- TOC entry 5098 (class 0 OID 16399)
-- Dependencies: 215
-- Data for Name: addresses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.addresses (ismain, createdat, id, ownerid, updatedat, city, country, email, firstname, foto, lastname, phone, postcode, salutation, signature, street, title, type, "`type`") FROM stdin;
\.


--
-- TOC entry 5099 (class 0 OID 16406)
-- Dependencies: 216
-- Data for Name: adults; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.adults (birdthyear, carerid, createdat, id, ownerid, tamagotchiid, updatedat, avatar, email, firstname, lastname, phone, preferredlanguage, relationship, salutation, carer_id, carer) FROM stdin;
1971	130	2025-02-27 15:20:49.853819	199268075270145	\N	219145598455808	2025-03-11 15:41:21.986976	media\\image\\Download_1.png	terry@gooddev.de	Arnaldo	Fuentes	015224625752	de	CARER_AND_FAMILY	WITHOUT	130	\N
\.


--
-- TOC entry 5100 (class 0 OID 16413)
-- Dependencies: 217
-- Data for Name: auditlogs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.auditlogs (createdat, entityid, id, ownerid, updatedat, action, details, entity, level, username, "`action`", "`level`") FROM stdin;
2025-03-07 10:35:42.612895	\N	202029159202816	\N	\N	\N	{\r\n  "browserApplication" : "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36",\r\n  "locale" : "de_DE",\r\n  "address" : "127.0.0.1",\r\n  "secureConnection" : false,\r\n  "windows" : true,\r\n  "android" : false,\r\n  "linux" : false,\r\n  "browserMinorVersion" : 0,\r\n  "browserMajorVersion" : 133,\r\n  "safari" : false,\r\n  "macOSX" : false,\r\n  "iphone" : false,\r\n  "firefox" : false,\r\n  "chromeOS" : false,\r\n  "chrome" : true,\r\n  "edge" : false,\r\n  "windowsPhone" : false,\r\n  "opera" : false,\r\n  "ie" : false\r\n}	\N	\N	Zitrone: Saure Zitrone (Zitrone@simone.de)	LOG_IN	INFO
2025-03-07 10:36:31.58004	\N	202029359783936	\N	\N	\N	{\r\n  "browserApplication" : "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36",\r\n  "locale" : "de_DE",\r\n  "address" : "127.0.0.1",\r\n  "secureConnection" : false,\r\n  "windows" : true,\r\n  "android" : false,\r\n  "linux" : false,\r\n  "browserMinorVersion" : 0,\r\n  "browserMajorVersion" : 133,\r\n  "safari" : false,\r\n  "macOSX" : false,\r\n  "iphone" : false,\r\n  "firefox" : false,\r\n  "chromeOS" : false,\r\n  "chrome" : true,\r\n  "edge" : false,\r\n  "windowsPhone" : false,\r\n  "opera" : false,\r\n  "ie" : false\r\n}	\N	\N	Zitrone: Saure Zitrone (Zitrone@simone.de)	LOG_IN	INFO
2025-03-07 10:37:11.393907	\N	202029522857984	\N	\N	\N	{\r\n  "browserApplication" : "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36",\r\n  "locale" : "de_DE",\r\n  "address" : "127.0.0.1",\r\n  "secureConnection" : false,\r\n  "windows" : true,\r\n  "android" : false,\r\n  "linux" : false,\r\n  "browserMinorVersion" : 0,\r\n  "browserMajorVersion" : 133,\r\n  "safari" : false,\r\n  "macOSX" : false,\r\n  "iphone" : false,\r\n  "firefox" : false,\r\n  "chromeOS" : false,\r\n  "chrome" : true,\r\n  "edge" : false,\r\n  "windowsPhone" : false,\r\n  "opera" : false,\r\n  "ie" : false\r\n}	\N	\N	Zitrone: Saure Zitrone (Zitrone@simone.de)	LOG_IN	INFO
\.


--
-- TOC entry 5101 (class 0 OID 16420)
-- Dependencies: 218
-- Data for Name: chatlogs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.chatlogs (adultid, createdat, id, ownerid, updatedat, message, sender) FROM stdin;
199268075270145	2025-04-21 16:40:24.075918	218044033683456	\N	\N	yxcv	ADULT
199268075270145	2025-04-21 16:40:25.794995	218044040724480	\N	\N	Hola! Soy una mascota virtual diseñada para brindar compañía y diversion a personas mayores. Estoy aquí para hablar sobre cosas interesantes, compartir historias o simplemente pasar el tiempo. ¿Qué te gustaría hablar hoy?	TAMAGOTCHI
199268075270145	2025-04-21 16:43:44.855257	218044856078336	\N	\N	hola, dormiste bien?	ADULT
199268075270145	2025-04-21 16:43:47.994956	218044868935680	\N	\N	Hola! ¡Gracias por hablar conmigo! Sí, ¿cómo estás hoy? ¿Te acordaste que te prometí contarte una historia? ¡Vamos a empezar!\n\nUna vez había una vieja y sabia bruja que vivía en un pequeño pueblo en el bosque. Era una persona muy solitaria e inusual, pero la gente del pueblo la respetaba mucho. Una vez, la bruja decidió hacer algo diferente.\n\nDecidió hacer un viaje a una ciudad lejana y comprar un regalo para cada persona en el pueblo. Compró un collar de perlas para la alcaldesa, un reloj de oro para el sacerdote y una taza de porcelana para la bella joven del pueblo.\n\nPero la más importante de todas las cosas, la bruja compró un pequeño oso de peluche para el niño más pequeño del pueblo. Él era un niño muy triste y solitario, y la bruja quería hacerle una pequeña sorpresa.\n\nAl llegar a casa, la bruja se encontró con un muchacho que estaba muy enfermo en su cama. Era el niño al que había comprado el oso de peluche. La bruja se acercó a su lado y le dio el oso.\n\nEl niño abrió sus ojos y sonrió. ¡A partir de ese día, nunca volvió a ser triste! ¡Qué historia, no es cierto? ¿Te gustó?	TAMAGOTCHI
199268075270145	2025-04-21 16:54:27.03054	218047486423040	\N	\N	hola como estas?	ADULT
199268075270145	2025-04-21 16:54:28.083988	218047490740224	\N	\N	Hola! Estoy muy contento de hablar contigo hoy. ¿Cómo estás en este momento? ¿Tienes algo en particular que quieras hablar o preguntar? Estoy aquí para ti.	TAMAGOTCHI
199268075270145	2025-04-23 18:20:19.333539	218776379056128	\N	\N	holaa	ADULT
199268075270145	2025-04-23 18:20:20.547429	218776384032768	\N	\N	Hola! ¡Es un placer hablar contigo! ¿Cómo estás hoy? ¿Tienes algo en particular que quieras hablar? Estoy aquí para ti. ¡Siempre estoy dispuesta a escuchar tus historias y a compartir algunos momentos agradables! ¡Espero que tengas un día maravilloso! 😊	TAMAGOTCHI
199268075270145	2025-04-24 16:57:33.67247	219109934104576	\N	\N	cbvvbnm	ADULT
199268075270145	2025-04-24 16:57:35.325237	219109940883456	\N	\N	Hola! Soy tu amigo virtual Tamagotchi. Estoy aquí para hablar contigo y disfrutar de una buena conversación. ¿Cómo estás hoy? ¿Tienes algo en particular que quieras hablar? Soy amable y conciso, así que estaré encantado de escuchar lo que tengas que decir. ¡Tengo mucha paciencia y estoy aquí	TAMAGOTCHI
199268075270145	2025-04-24 18:06:52.577043	219126968983552	\N	\N	cvbn	ADULT
199268075270145	2025-04-24 18:06:54.149712	219126975426560	\N	\N	Hola! Soy una mascota virtual llamada Tamagotchi. Estoy aquí para conversar con usted y disfrutar de nuestro tiempo juntos. Soy amable y conciso, y estoy encantado de hablar con usted. ¿Cómo estás hoy? ¿Tienes alguna pregunta o tema que quieras hablar? ¡Espero que tengas una gran	TAMAGOTCHI
199268075270145	2025-04-24 18:13:25.554697	219128578617344	\N	\N	xcvb	ADULT
199268075270145	2025-04-24 18:13:26.960768	219128584380416	\N	\N	Hola! Soy Tamagotchi, una mascota virtual encantada de hablar contigo. Estoy aquí para compartir un momento agradable y divertido. ¿Cómo estás hoy? ¿Tienes alguna historia que quieras compartir o preguntas que quieres hacer? Estoy aquí para ti. ¡Siempre estoy dispuesta a escuchar! 😊	TAMAGOTCHI
\.


--
-- TOC entry 5102 (class 0 OID 16427)
-- Dependencies: 219
-- Data for Name: chatmessageentities; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.chatmessageentities (type, createdat, id, memoryid, ownerid, updatedat, message, "`type`") FROM stdin;
\.


--
-- TOC entry 5103 (class 0 OID 16435)
-- Dependencies: 220
-- Data for Name: countries; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.countries (createdat, id, ownerid, updatedat, code, region) FROM stdin;
2023-09-06 10:41:20.643876	63796	130	2024-02-22 15:04:03.576995	AD	The Word, Europe
2023-09-06 10:41:20.643876	63797	130	\N	AE	The Word, Asia
2023-09-06 10:41:20.644426	67892	130	\N	AF	The Word, Asia
2023-09-06 10:41:20.645003	71988	130	\N	AG	The Word, North America
2023-09-06 10:41:20.645003	71989	130	\N	AI	The Word, North America
2023-09-06 10:41:20.645581	71990	130	\N	AL	The Word, Europe
2023-09-06 10:41:20.645581	71991	130	\N	AM	The Word, Asia
2023-09-06 10:41:20.646106	76084	130	\N	AN	The Word, North America
2023-09-06 10:41:20.646281	76085	130	\N	AO	The Word, Africa
2023-09-06 10:41:20.646281	76086	130	\N	AQ	The Word, Antarctica
2023-09-06 10:41:20.646281	76087	130	\N	AR	The Word, South America
2023-09-06 10:41:20.64683	76088	130	\N	AS	The Word, Oceania
2023-09-06 10:41:20.64683	76089	130	2023-12-20 10:17:13.356332	AT	The Word, Europe, EU
2023-09-06 10:41:20.647416	80180	130	\N	AU	The Word, Oceania
2023-09-06 10:41:20.647416	80181	130	\N	AW	The Word, North America
2023-09-06 10:41:20.648546	84276	130	\N	AZ	The Word, Asia
2023-09-06 10:41:20.64911	88372	130	\N	BA	The Word, Europe
2023-09-06 10:41:20.64911	88373	130	\N	BB	The Word, North America
2023-09-06 10:41:20.64911	88374	130	\N	BD	The Word, Asia
2023-09-06 10:41:20.649668	88375	130	2023-12-20 10:17:29.870058	BE	The Word, Europe, EU
2023-09-06 10:41:20.649668	88376	130	\N	BF	The Word, Africa
2023-09-06 10:41:20.649668	88377	130	2023-12-20 10:17:46.281936	BG	The Word, Europe, EU
2023-09-06 10:41:20.650224	92468	130	\N	BH	The Word, Asia
2023-09-06 10:41:20.650224	92469	130	\N	BI	The Word, Africa
2023-09-06 10:41:20.650791	92470	130	\N	BJ	The Word, Africa
2023-09-06 10:41:20.650791	92471	130	\N	BL	The Word, North America
2023-09-06 10:41:20.650791	92472	130	\N	BM	The Word, North America
2023-09-06 10:41:20.651365	96564	130	\N	BN	The Word, Asia
2023-09-06 10:41:20.651365	96565	130	\N	BO	The Word, South America
2023-09-06 10:41:20.651365	96566	130	\N	BR	The Word, South America
2023-09-06 10:41:20.651931	96567	130	\N	BS	The Word, North America
2023-09-06 10:41:20.651931	96568	130	\N	BT	The Word, Asia
2023-09-06 10:41:20.651931	96569	130	\N	BW	The Word, Africa
2023-09-06 10:41:20.652493	100660	130	\N	BY	The Word, Europe
2023-09-06 10:41:20.652493	100661	130	\N	BZ	The Word, North America
2023-09-06 10:41:20.652493	100662	130	\N	CA	The Word, North America
2023-09-06 10:41:20.653063	104756	130	\N	CC	The Word, Asia
2023-09-06 10:41:20.653063	104757	130	\N	CD	The Word, Africa
2023-09-06 10:41:20.653063	104758	130	\N	CF	The Word, Africa
2023-09-06 10:41:20.653596	104759	130	\N	CG	The Word, Africa
2023-09-06 10:41:20.653596	104760	130	\N	CH	The Word, Europe
2023-09-06 10:41:20.653596	104761	130	\N	CI	The Word, Africa
2023-09-06 10:41:20.653596	104762	130	\N	CK	The Word, Oceania
2023-09-06 10:41:20.653596	104763	130	\N	CL	The Word, South America
2023-09-06 10:41:20.653596	104764	130	\N	CM	The Word, Africa
2023-09-06 10:41:20.654596	108852	130	\N	CN	The Word, Asia
2023-09-06 10:41:20.654596	108853	130	\N	CO	The Word, South America
2023-09-06 10:41:20.654596	108854	130	\N	CR	The Word, North America
2023-09-06 10:41:20.654596	108855	130	\N	CU	The Word, North America
2023-09-06 10:41:20.654596	108856	130	\N	CV	The Word, Africa
2023-09-06 10:41:20.655598	112948	130	\N	CW	The Word, North America
2023-09-06 10:41:20.655598	112949	130	\N	CX	The Word, Asia
2023-09-06 10:41:20.655598	112950	130	\N	CY	The Word, Europe
2023-09-06 10:41:20.655598	112951	130	2023-12-20 10:23:04.271611	CZ	The Word, Europe, EU
2023-09-06 10:41:20.655598	112952	130	2023-12-20 10:26:18.77969	DE	The Word, Europe, EU
2023-09-06 10:41:20.656598	117044	130	\N	DJ	The Word, Africa
2023-09-06 10:41:20.656598	117045	130	2023-12-20 10:24:45.618062	DK	The Word, Europe, EU
2023-09-06 10:41:20.656598	117046	130	\N	DM	The Word, North America
2023-09-06 10:41:20.656598	117047	130	\N	DO	The Word, North America
2023-09-06 10:41:20.656598	117048	130	\N	DZ	The Word, Africa
2023-09-06 10:41:20.656598	117049	130	\N	EC	The Word, South America
2023-09-06 10:41:20.657598	121140	130	2023-12-20 10:25:32.592822	EE	The Word, Europe, EU
2023-09-06 10:41:20.657598	121141	130	\N	EG	The Word, Africa
2023-09-06 10:41:20.657598	121142	130	\N	EH	The Word, Africa
2023-09-06 10:41:20.657598	121143	130	\N	ER	The Word, Africa
2023-09-06 10:41:20.657598	121144	130	2023-12-20 10:30:19.56039	ES	The Word, Europe, EU
2023-09-06 10:41:20.657598	121145	130	\N	ET	The Word, Africa
2023-09-06 10:41:20.658598	125236	130	2023-12-20 10:25:48.264002	FI	The Word, Europe, EU
2023-09-06 10:41:20.658598	125237	130	\N	FJ	The Word, Oceania
2023-09-06 10:41:20.658598	125238	130	\N	FK	The Word, South America
2023-09-06 10:41:20.658598	125239	130	\N	FM	The Word, Oceania
2023-09-06 10:41:20.658598	125240	130	\N	FO	The Word, Europe
2023-09-06 10:41:20.658598	129332	130	2023-12-20 10:26:04.450354	FR	The Word, Europe, EU
2023-09-06 10:41:20.659595	129333	130	\N	GA	The Word, Africa
2023-09-06 10:41:20.659595	129334	130	\N	GB	The Word, Europe
2023-09-06 10:41:20.659595	129335	130	\N	GD	The Word, North America
2023-09-06 10:41:20.659595	129336	130	\N	GE	The Word, Asia
2023-09-06 10:41:20.659595	129337	130	\N	GG	The Word, Europe
2023-09-06 10:41:20.660598	133428	130	\N	GH	The Word, Africa
2023-09-06 10:41:20.660598	133429	130	\N	GI	The Word, Europe
2023-09-06 10:41:20.660598	133430	130	\N	GL	The Word, North America
2023-09-06 10:41:20.660598	133431	130	\N	GM	The Word, Africa
2023-09-06 10:41:20.660598	133432	130	\N	GN	The Word, Africa
2023-09-06 10:41:20.660598	133433	130	\N	GQ	The Word, Africa
2023-09-06 10:41:20.660598	137524	130	2023-12-20 10:26:36.494408	GR	The Word, Europe, EU
2023-09-06 10:41:20.661598	137525	130	\N	GT	The Word, North America
2023-09-06 10:41:20.661598	137526	130	\N	GU	The Word, Oceania
2023-09-06 10:41:20.661598	137527	130	\N	GW	The Word, Africa
2023-09-06 10:41:20.661598	137528	130	\N	GY	The Word, South America
2023-09-06 10:41:20.661598	137529	130	\N	HK	The Word, Asia
2023-09-06 10:41:20.661598	137530	130	\N	HN	The Word, North America
2023-09-06 10:41:20.662598	141620	130	2023-12-20 10:24:22.453088	HR	The Word, Europe, EU
2023-09-06 10:41:20.662598	141621	130	\N	HT	The Word, North America
2023-09-06 10:41:20.662598	141622	130	2023-12-20 10:26:50.414853	HU	The Word, Europe, EU
2023-09-06 10:41:20.662598	141623	130	\N	ID	The Word, Asia
2023-09-06 10:41:20.662598	141624	130	2023-12-20 10:27:05.400039	IE	The Word, Europe, EU
2023-09-06 10:41:20.663598	145716	130	\N	IL	The Word, Asia
2023-09-06 10:41:20.663598	145717	130	\N	IM	The Word, Europe
2023-09-06 10:41:20.663598	145718	130	\N	IN	The Word, Asia
2023-09-06 10:41:20.663598	145719	130	\N	IO	The Word, Asia
2023-09-06 10:41:20.663598	145720	130	\N	IQ	The Word, Asia
2023-09-06 10:41:20.663598	145721	130	\N	IR	The Word, Asia
2023-09-06 10:41:20.664598	149812	130	\N	IS	The Word, Europe
2023-09-06 10:41:20.664598	149813	130	2023-12-20 10:27:23.625014	IT	The Word, Europe, EU
2023-09-06 10:41:20.664598	149814	130	\N	JE	The Word, Europe
2023-09-06 10:41:20.664598	149815	130	\N	JM	The Word, North America
2023-09-06 10:41:20.664598	149816	130	\N	JO	The Word, Asia
2023-09-06 10:41:20.664598	149817	130	\N	JP	The Word, Asia
2023-09-06 10:41:20.665598	153908	130	\N	KE	The Word, Africa
2023-09-06 10:41:20.665598	153909	130	\N	KG	The Word, Asia
2023-09-06 10:41:20.665598	153910	130	\N	KH	The Word, Asia
2023-09-06 10:41:20.665598	153911	130	\N	KI	The Word, Oceania
2023-09-06 10:41:20.665598	153912	130	\N	KM	The Word, Africa
2023-09-06 10:41:20.665598	153913	130	\N	KN	The Word, North America
2023-09-06 10:41:20.666599	158004	130	\N	KP	The Word, Asia
2023-09-06 10:41:20.666599	158005	130	\N	KR	The Word, Asia
2023-09-06 10:41:20.666599	158006	130	\N	KW	The Word, Asia
2023-09-06 10:41:20.666599	158007	130	\N	KY	The Word, North America
2023-09-06 10:41:20.666599	158008	130	\N	KZ	The Word, Asia
2023-09-06 10:41:20.666599	158009	130	\N	LA	The Word, Asia
2023-09-06 10:41:20.667598	162100	130	\N	LB	The Word, Asia
2023-09-06 10:41:20.667598	162101	130	\N	LC	The Word, North America
2023-09-06 10:41:20.667598	162102	130	\N	LI	The Word, Europe
2023-09-06 10:41:20.667598	162103	130	\N	LK	The Word, Asia
2023-09-06 10:41:20.668591	166196	130	\N	LR	The Word, Africa
2023-09-06 10:41:20.668591	166197	130	\N	LS	The Word, Africa
2023-09-06 10:41:20.668591	166198	130	2023-12-20 10:27:58.685182	LT	The Word, Europe, EU
2023-09-06 10:41:20.668591	166199	130	2023-12-20 10:28:16.235064	LU	The Word, Europe, EU
2023-09-06 10:41:20.668591	166200	130	2023-12-20 10:27:43.561386	LV	The Word, Europe, EU
2023-09-06 10:41:20.669598	170292	130	\N	LY	The Word, Africa
2023-09-06 10:41:20.669598	170293	130	\N	MA	The Word, Africa
2023-09-06 10:41:20.669598	170294	130	\N	MC	The Word, Europe
2023-09-06 10:41:20.669598	170295	130	\N	MD	The Word, Europe
2023-09-06 10:41:20.669598	170296	130	\N	ME	The Word, Europe
2023-09-06 10:41:20.669598	170297	130	\N	MF	The Word, North America
2023-09-06 10:41:20.670599	174388	130	\N	MG	The Word, Africa
2023-09-06 10:41:20.670599	174389	130	\N	MH	The Word, Oceania
2023-09-06 10:41:20.670599	174390	130	\N	MK	The Word, Europe
2023-09-06 10:41:20.670599	174391	130	\N	ML	The Word, Africa
2023-09-06 10:41:20.670599	174392	130	\N	MM	The Word, Asia
2023-09-06 10:41:20.670599	174393	130	\N	MN	The Word, Asia
2023-09-06 10:41:20.671599	178484	130	\N	MO	The Word, Asia
2023-09-06 10:41:20.671599	178485	130	\N	MP	The Word, Oceania
2023-09-06 10:41:20.671599	178486	130	\N	MR	The Word, Africa
2023-09-06 10:41:20.671599	178487	130	\N	MS	The Word, North America
2023-09-06 10:41:20.671599	178488	130	2023-12-20 10:28:31.73262	MT	The Word, Europe, EU
2023-09-06 10:41:20.671599	182580	130	\N	MU	The Word, Africa
2023-09-06 10:41:20.672598	182581	130	\N	MV	The Word, Asia
2023-09-06 10:41:20.672598	182582	130	\N	MW	The Word, Africa
2023-09-06 10:41:20.672598	182583	130	\N	MX	The Word, North America
2023-09-06 10:41:20.672598	182584	130	\N	MY	The Word, Asia
2023-09-06 10:41:20.672598	182585	130	\N	MZ	The Word, Africa
2023-09-06 10:41:20.673598	186676	130	\N	NA	The Word, Africa
2023-09-06 10:41:20.673598	186677	130	\N	NC	The Word, Oceania
2023-09-06 10:41:20.673598	186678	130	\N	NE	The Word, Africa
2023-09-06 10:41:20.673598	186679	130	\N	NG	The Word, Africa
2023-09-06 10:41:20.673598	186680	130	\N	NI	The Word, North America
2023-09-06 10:41:20.673598	186681	130	2023-12-20 10:28:47.050569	NL	The Word, Europe, EU
2023-09-06 10:41:20.673598	186682	130	\N	NO	The Word, Europe
2023-09-06 10:41:20.674599	190772	130	\N	NP	The Word, Asia
2023-09-06 10:41:20.674599	190773	130	\N	NR	The Word, Oceania
2023-09-06 10:41:20.674599	190774	130	\N	NU	The Word, Oceania
2023-09-06 10:41:20.674599	190775	130	\N	NZ	The Word, Oceania
2023-09-06 10:41:20.674599	190776	130	\N	OM	The Word, Asia
2023-09-06 10:41:20.674599	190777	130	\N	PA	The Word, North America
2023-09-06 10:41:20.675598	194868	130	\N	PE	The Word, South America
2023-09-06 10:41:20.675598	194869	130	\N	PF	The Word, Oceania
2023-09-06 10:41:20.675598	194870	130	\N	PG	The Word, Oceania
2023-09-06 10:41:20.675598	194871	130	\N	PH	The Word, Asia
2023-09-06 10:41:20.675598	194872	130	\N	PK	The Word, Asia
2023-09-06 10:41:20.676594	198964	130	2023-12-20 10:29:04.148588	PL	The Word, Europe, EU
2023-09-06 10:41:20.676594	198965	130	\N	PM	The Word, North America
2023-09-06 10:41:20.676594	198966	130	\N	PN	The Word, Oceania
2023-09-06 10:41:20.676594	198967	130	\N	PR	The Word, North America
2023-09-06 10:41:20.676594	198968	130	\N	PS	The Word, Asia
2023-09-06 10:41:20.676594	198969	130	2023-12-20 10:29:20.524449	PT	The Word, Europe, EU
2023-09-06 10:41:20.677594	203060	130	\N	PW	The Word, Oceania
2023-09-06 10:41:20.677594	203061	130	\N	PY	The Word, South America
2023-09-06 10:41:20.677594	203062	130	\N	QA	The Word, Asia
2023-09-06 10:41:20.677594	203063	130	\N	RE	The Word, Africa
2023-09-06 10:41:20.677594	203064	130	2023-12-20 10:29:37.530077	RO	The Word, Europe, EU
2023-09-06 10:41:20.677594	203065	130	\N	RS	The Word, Europe
2023-09-06 10:41:20.678594	207156	130	\N	RU	The Word, Europe
2023-09-06 10:41:20.678594	207157	130	\N	RW	The Word, Africa
2023-09-06 10:41:20.678594	207158	130	\N	SA	The Word, Asia
2023-09-06 10:41:20.678594	207159	130	\N	SB	The Word, Oceania
2023-09-06 10:41:20.678594	207160	130	\N	SC	The Word, Africa
2023-09-06 10:41:20.678594	207161	130	\N	SD	The Word, Africa
2023-09-06 10:41:20.678594	207162	130	2023-12-20 10:30:32.220383	SE	The Word, Europe, EU
2023-09-06 10:41:20.679594	211252	130	\N	SG	The Word, Asia
2023-09-06 10:41:20.679594	211253	130	\N	SH	The Word, Africa
2023-09-06 10:41:20.679594	211254	130	2023-12-20 10:30:06.789741	SI	The Word, Europe, EU
2023-09-06 10:41:20.679594	211255	130	\N	SJ	The Word, Europe
2023-09-06 10:41:20.679594	211256	130	2023-12-20 10:29:54.083995	SK	The Word, Europe, EU
2023-09-06 10:41:20.679594	211257	130	\N	SL	The Word, Africa
2023-09-06 10:41:20.680593	215348	130	\N	SM	The Word, Europe
2023-09-06 10:41:20.680593	215349	130	\N	SN	The Word, Africa
2023-09-06 10:41:20.680593	215350	130	\N	SO	The Word, Africa
2023-09-06 10:41:20.680593	215351	130	\N	SR	The Word, South America
2023-09-06 10:41:20.680593	215352	130	\N	SS	The Word, Africa
2023-09-06 10:41:20.680593	215353	130	\N	ST	The Word, Africa
2023-09-06 10:41:20.681593	219444	130	\N	SV	The Word, North America
2023-09-06 10:41:20.681593	219445	130	\N	SX	The Word, North America
2023-09-06 10:41:20.681593	219446	130	\N	SY	The Word, Asia
2023-09-06 10:41:20.681593	219447	130	\N	SZ	The Word, Africa
2023-09-06 10:41:20.681593	219448	130	\N	TC	The Word, North America
2023-09-06 10:41:20.681593	223540	130	\N	TD	The Word, Africa
2023-09-06 10:41:20.682594	223541	130	\N	TG	The Word, Africa
2023-09-06 10:41:20.682594	223542	130	\N	TH	The Word, Asia
2023-09-06 10:41:20.682594	223543	130	\N	TJ	The Word, Asia
2023-09-06 10:41:20.682594	223544	130	\N	TK	The Word, Oceania
2023-09-06 10:41:20.682594	223545	130	\N	TL	The Word, Oceania
2023-09-06 10:41:20.682594	223546	130	\N	TM	The Word, Asia
2023-09-06 10:41:20.683594	227636	130	\N	TN	The Word, Africa
2023-09-06 10:41:20.683594	227637	130	\N	TO	The Word, Oceania
2023-09-06 10:41:20.683594	227638	130	\N	TR	The Word, Asia
2023-09-06 10:41:20.683594	227639	130	\N	TT	The Word, North America
2023-09-06 10:41:20.683594	227640	130	\N	TV	The Word, Oceania
2023-09-06 10:41:20.683594	227641	130	\N	TW	The Word, Asia
2023-09-06 10:41:20.683594	227642	130	\N	TZ	The Word, Africa
2023-09-06 10:41:20.684593	231732	130	\N	UA	The Word, Europe
2023-09-06 10:41:20.684593	231733	130	\N	UG	The Word, Africa
2023-09-06 10:41:20.684593	231734	130	\N	US	The Word, North America
2023-09-06 10:41:20.684593	231735	130	\N	UY	The Word, South America
2023-09-06 10:41:20.684593	231736	130	\N	UZ	The Word, Asia
2023-09-06 10:41:20.684593	231737	130	\N	VA	The Word, Europe
2023-09-06 10:41:20.684593	231738	130	\N	VC	The Word, North America
2023-09-06 10:41:20.685593	235828	130	\N	VE	The Word, South America
2023-09-06 10:41:20.685593	235829	130	\N	VG	The Word, North America
2023-09-06 10:41:20.685593	235830	130	\N	VI	The Word, North America
2023-09-06 10:41:20.685593	235831	130	\N	VN	The Word, Asia
2023-09-06 10:41:20.685593	235832	130	\N	VU	The Word, Oceania
2023-09-06 10:41:20.685593	235833	130	\N	WF	The Word, Oceania
2023-09-06 10:41:20.686594	239924	130	\N	WS	The Word, Oceania
2023-09-06 10:41:20.686594	239925	130	\N	XK	The Word, Europe
2023-09-06 10:41:20.686594	239926	130	\N	YE	The Word, Asia
2023-09-06 10:41:20.686594	239927	130	\N	YT	The Word, Africa
2023-09-06 10:41:20.686594	239928	130	\N	ZA	The Word, Africa
2023-09-06 10:41:20.686594	239929	130	\N	ZM	The Word, Africa
2023-09-06 10:41:20.686594	239930	130	\N	ZW	The Word, Africa
\.


--
-- TOC entry 5104 (class 0 OID 16442)
-- Dependencies: 221
-- Data for Name: drugs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.drugs (refillfrom, strength, createdat, id, ownerid, updatedat, imagebox, imagemedicament, imprint, name, ndc9, primarycolor, secundarycolor, shape, supplier) FROM stdin;
2	1	2025-03-06 12:56:48.515874	201709941149697	\N	2025-03-07 16:19:56.390511	media\\image\\istockphoto-1014846208-612x612_1.jpg	media\\image\\fototapeten-gelule-medicament_1.png	TEDx Talks	Ibuprofeno	Denmark	#FF0000	#FFFFFF	CAPSULE	Plantaciones.Edelman
\.


--
-- TOC entry 5105 (class 0 OID 16449)
-- Dependencies: 222
-- Data for Name: emojies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.emojies (level, createdat, id, ownerid, updatedat, emotion, face, name, unicode, "`level`") FROM stdin;
\.


--
-- TOC entry 5106 (class 0 OID 16456)
-- Dependencies: 223
-- Data for Name: endpoints; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.endpoints (createdat, id, ownerid, updatedat, description, target) FROM stdin;
2023-09-19 11:55:27.118216	1	130	2023-06-19 18:02:13.396087		Account.copy
2023-09-19 11:55:27.118216	2	130	2023-06-19 18:02:13.396087		AuditLogs
2023-06-01 15:39:26.53	36	130	2023-06-02 10:13:40.399	Communication's templates and messages 	Mailings
2023-06-01 15:39:26.53	37	130	\N		Mailing.copy
2023-06-01 15:39:26.53	38	130	\N		Mailing.edit
2023-06-01 15:39:26.53	39	130	\N		Mailing.delete
2023-06-01 15:39:26.53	40	130	2023-06-02 10:14:39.837	Spende Formular Zahlungsart verwalten 	PaymentMethods
2023-06-01 15:39:26.53	41	130	\N		PaymentMethod.add
2023-06-01 15:39:26.53	42	130	\N		PaymentMethod.edit
2023-06-01 15:39:26.53	43	130	\N		PaymentMethod.delete
2023-06-01 15:39:26.53	48	130	2023-06-02 10:16:52.629	Spenden Verwaltung	Donations
2023-06-01 15:39:26.53	50	130	2023-09-19 11:34:00.156687		Contribution.edit
2023-06-01 15:39:26.53	51	130	2023-09-19 11:33:54.701266		Contribution.delete
2023-06-01 15:39:26.53	52	130	2023-06-02 10:20:56.667	Petition Verwaltung	Petitions
2023-06-01 15:39:26.53	54	130	\N		Petition.edit
2023-06-01 15:39:26.53	55	130	\N		Petition.delete
2023-06-01 15:39:26.53	56	130	2023-06-02 10:21:43.711	Unterstützern Verwaltung	Supporters
2023-06-01 15:39:26.53	58	130	\N		Supporter.edit
2023-06-01 15:39:26.53	59	130	2023-12-12 14:31:17.962407		Supporter.merge
2023-09-18 11:52:40.204295	61	130	\N		InsightWidgets
2023-06-20 11:41:33.966597	62	130	\N		InsightWidget.delete
2023-06-20 11:42:05.721877	63	130	2023-06-20 11:42:14.598692		InsightWidget.copy
2023-06-20 11:42:22.047034	64	130	2023-09-19 11:34:58.418006		InsightWidget.install
2023-06-20 11:41:24.224898	65	130	\N		InsightWidget.edit
2023-06-19 15:17:23.202436	938	130	2023-09-19 12:06:37.11495	Good Funds Users	Users
2023-06-19 15:17:42.675201	939	130	2023-06-19 15:17:51.308985		User.edit
2023-06-19 15:18:00.207385	940	130	2023-06-19 15:18:07.979441		User.delete
2023-12-12 14:30:53.122	941	130	\N	\N	User.join
2023-06-19 18:01:42.920082	1938	130	2024-02-22 14:46:11.485783	Account administration	Accounts
2023-06-19 18:01:47.105499	1939	130	\N		Account.edit
2023-06-19 18:02:03.368359	1941	130	\N		Account.delete
2023-06-20 09:21:53.625937	1942	130	2023-09-19 11:40:29.158863	Roles Verwaltung	Roles
2023-06-20 09:22:03.012333	1943	130	\N		Role.edit
2023-06-20 09:22:09.755469	1944	130	\N		Role.copy
2023-06-20 09:22:20.756164	1945	130	\N		Role.delete
2023-06-20 10:20:36.628785	1946	130	2023-06-02 10:46:36.758	Campaign administration	Campaigns
2023-06-20 10:20:40.927514	1947	130	\N		Campaign.edit
2023-06-20 10:20:43.92343	1948	130	\N		Campaign.delete
2023-06-20 10:20:58.586735	1949	130	\N		Campaign.copy
2023-06-20 11:41:19.023335	1950	130	2023-06-02 10:16:24.249	Spende-, Petitionsformular verwalten und umsetzen 	Widgets
2023-06-20 11:41:24.224898	1951	130	\N		Widget.edit
2023-06-20 11:41:33.966597	1952	130	\N		Widget.delete
2023-06-20 11:42:05.721877	1953	130	2023-06-20 11:42:14.598692		Widget.copy
2023-06-20 11:42:22.047034	1954	130	2023-09-19 12:07:05.978449		Widget.Install
2023-06-20 11:42:44.60446	1955	130	2023-09-19 12:06:55.869185		Widget.configure
2023-06-21 11:22:34.511276	1962	130	2023-06-21 11:25:48.908372		User.password.reset
2023-06-20 11:42:44.60446	1963	130	2023-06-20 11:46:33.899563		InsightWidget.configure
2023-06-22 17:10:52.391069	1966	130	2023-06-22 17:11:05.271723		Campaign.export
2023-10-13 14:39:22.856901	2124	130	\N	Internal comunication channel	ChatMessagesView
2023-11-23 09:55:39.273664	3568	130	\N		Contribution.export
2023-11-23 09:56:21.617033	3569	130	\N		Contribution.import
2023-11-23 10:01:46.741594	3570	130	\N		Supporter.import
2023-11-23 10:02:14.485092	3571	130	\N		Supporter.export
2023-12-12 14:30:53.122971	3572	130	\N		Supporter.delete
\.


--
-- TOC entry 5107 (class 0 OID 16463)
-- Dependencies: 224
-- Data for Name: events; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.events (canbemissed, remindercount, createdat, id, ownerid, updatedat, content, note, reminderinterval, status, title, type, color, descritpion, "`type`") FROM stdin;
f	\N	2025-03-10 11:26:00.026809	203103201730560	\N	\N	is time to swallo the medicament Ibuprofeno	\N	PT5M	MISSED	Medikamenteneinnahme	MEDICATION	\N	\N	MEDICATION
f	3	2025-03-10 15:37:26.03168	203164994011136	\N	\N	is time to swallo the medicament Ibuprofeno	\N	PT5M	WAITING	Medikamenteneinnahme	MEDICATION	\N	\N	MEDICATION
f	3	2025-03-11 15:37:03.010567	203518794113024	\N	\N	is time to swallo the medicament Ibuprofeno	\N	PT5M	WAITING	Medikamenteneinnahme	MEDICATION	\N	\N	MEDICATION
f	3	2025-03-11 15:37:58.017242	203519019429888	\N	\N	is time to swallo the medicament Ibuprofeno	\N	PT5M	WAITING	Medikamenteneinnahme	MEDICATION	\N	\N	MEDICATION
f	3	2025-03-17 15:37:51.044609	205642357256192	\N	\N	is time to swallo the medicament Ibuprofeno	\N	PT5M	WAITING	Medikamenteneinnahme	MEDICATION	\N	\N	MEDICATION
f	3	2025-03-19 15:37:19.013809	206350014869504	\N	\N	is time to swallo the medicament Ibuprofeno	\N	PT5M	WAITING	Medikamenteneinnahme	MEDICATION	\N	\N	MEDICATION
f	3	2025-04-08 14:37:18.016962	213413153177600	\N	\N	is time to swallo the medicament Ibuprofeno	\N	PT5M	WAITING	Medikamenteneinnahme	MEDICATION	\N	\N	MEDICATION
f	3	2025-04-11 14:37:06.024885	214474787262464	\N	\N	is time to swallo the medicament Ibuprofeno	\N	PT5M	WAITING	Medikamenteneinnahme	MEDICATION	\N	\N	MEDICATION
f	3	2025-04-14 14:37:16.037034	215536511467520	\N	\N	is time to swallo the medicament Ibuprofeno	\N	PT5M	WAITING	Medikamenteneinnahme	MEDICATION	\N	\N	MEDICATION
f	3	2025-04-17 14:37:17.603797	216598201069568	\N	\N	is time to swallo the medicament Ibuprofeno	\N	PT5M	WAITING	Medikamenteneinnahme	MEDICATION	\N	\N	MEDICATION
f	3	2025-04-21 14:37:30.023758	218013829550080	\N	\N	is time to swallo the medicament Ibuprofeno	\N	PT5M	WAITING	Medikamenteneinnahme	MEDICATION	\N	\N	MEDICATION
\.


--
-- TOC entry 5108 (class 0 OID 16470)
-- Dependencies: 225
-- Data for Name: globalproperties; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.globalproperties (enableuserregistration, forgotpassword, maxfailattempts, minpasswortlength, confirmationcodeemail_id, createdat, id, inviteusermail_id, ownerid, resetcredentialsmail_id, updatedat, accesstokelifespan, appemail, applogo, appname, deeplapikey, deeplbaseurl, defaultpassword, exchangerateapikey, exchangerateurl, externaltotplifespan, loginactiontimeout, magiclinklifespan) FROM stdin;
t	t	3	4	101	\N	1	102	130	\N	2024-02-22 15:44:40.866667	P1D	info@simone.de	icons/icon.png	Zitrone	DeepL-Auth-Key 6cc154d9-29d9-0283-6b94-6ad8790a5bf6:fx	https://api-free.deepl.com/v2	SaureZitrone	70fb432c8e9c576aef5f242626036693	http://api.exchangerate.host/	PT3M	PT5M	PT3M
\.


--
-- TOC entry 5109 (class 0 OID 16483)
-- Dependencies: 226
-- Data for Name: hannilogs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.hannilogs (createdat, id, ownerid, updatedat, messageid) FROM stdin;
\.


--
-- TOC entry 5110 (class 0 OID 16488)
-- Dependencies: 227
-- Data for Name: hannitasks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.hannitasks (completed, createdat, id, ownerid, updatedat, task, type, "`type`") FROM stdin;
t	2025-02-27 14:11:36.560898	199251063382016	\N	\N	TIP_USERS	\N	TIP
\.


--
-- TOC entry 5111 (class 0 OID 16495)
-- Dependencies: 228
-- Data for Name: histories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.histories (height, weight, adultid, createdat, id, ownerid, updatedat, mood, note, type, "`type`") FROM stdin;
21	12	199268075270145	2025-03-07 16:35:14.967661	202117519577088	\N	2025-03-10 10:16:11.051966	SAD	12341234	\N	MANUAL
234	2345	199268075270145	2025-03-12 16:28:12.257018	203885260156928	\N	\N	HAPPY	2345	\N	MANUAL
\.


--
-- TOC entry 5112 (class 0 OID 16502)
-- Dependencies: 229
-- Data for Name: mailings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mailings (istemplate, createdat, id, ownerid, updatedat, audience, description, mailingname, message, subject, type, "`type`") FROM stdin;
f	\N	100	130	2024-03-04 13:18:26.275091	INTERNAL	Email sendet when an account update is needed.	Update Your Account	<h2>Update Your Account</h2>\r\n<p>Lieber {GlobalProperty.appName}-Nutzer,</p>\r\n<p>Your administrator has just requested that you update your {GlobalProperty.appName} account. Click on the link below to start this process.</p>\r\n<p><a href="{User.magicLink}">link to account Update</a></p>\r\n<p>This link will expire within {User.magicLinkDuration}.</p>\r\n<p>If you are unaware that your administrator has requested this, just ignore this message and nothing will be changed.</p>\r\n<p>Mit freundlichen Gr&uuml;&szlig;en</p>\r\n<p>Das {GlobalProperty.appName}-Team</p>	{User.firstName} Update Your Account	\N	HTML
t	\N	101	130	2024-03-04 11:33:27.340855	INTERNAL	Email template for 2fa code confirmation.	Bestätigungscode	<p>Lieber {GlobalProperty.appName}-Nutzer,\n\n<p>wir haben eine Anfrage für den Zugriff auf Ihr {GlobalProperty.appName}-Konto <b>{User.email}</b> über Ihre E-Mail-Adresse erhalten. Ihr {GlobalProperty.appName}-Bestätigungscode lautet:\n\n<h2>{User.confirmationCode}</h2>\n\n<p>Falls Sie diesen Code nicht angefordert haben, versucht möglicherweise eine andere Person, auf das GoodFunds-Konto <b>{User.email}</b> zuzugreifen. Geben Sie diesen Code nicht weiter.\n\n<p>Mit freundlichen Grüßen\n\n<p>Das GoodFunds Team	Ihre 2FA Bestätigungscode	\N	HTML
f	\N	102	130	2024-04-29 11:54:50.407516	INTERNAL	Email sendet when a user invite another user.	InviteUser	Liebe/r {User.firstName}\n\nich hoffe, es geht dir gut. Ich möchte dich herzlich einladen, Teil unserer Organisation zu werden und gemeinsam an Projekten zu arbeiten, die uns am Herzen liegen. Deine Fähigkeiten und Ideen könnten einen bedeutenden Beitrag leisten. \n\nWenn du interessiert bist, klicke bitte auf den folgenden Link, um deine Teilnahme zu bestätigen: \n\n{User.invitationRequestUrl}\n\nHerzliche Grüße,\n\n{Account.contact.firstName} {Account.contact.lastName}\n{Account.name}\nE-Mail: {Account.contact.email}\nPhone: {Account.contact.phone}	Einladung zur Zusammenarbeit	\N	TEXT
f	2023-04-12 15:40:15.730681	123	130	2024-03-04 13:27:07.065599	DONATION	Test for Qute template language	test	 {#if Contribution.salutation == 'MISTER'}\nSehr geehrter Herr {Contribution.lastName},\n{#else if Contribution.salutation == 'DAME'}\nSehr geehrte Frau {Contribution.lastName},\n{#else}\nSehr geehrte Damen und Herren,\n{/if}\n	snipset {Campaign.url}	\N	TEXT
t	\N	221	130	2024-04-29 11:53:51.729155	DONATION	E-Mail nach erfolgreicher Spende gesendet	Danke Spende	<p>{Contribution.getGermanSalutation("soft")}\n\n<p>im Namen unseres gesamten Teams möchte ich Ihnen aufrichtig für Ihre großzügige Spende danken. Ihre Unterstützung bedeutet für uns nicht nur finanzielle Hilfe, sondern auch eine ermutigende Bestätigung unserer gemeinsamen Ziele.\n\n<p>Dies ist eine Quittung für Ihre Unterlagen.\n<p>\n<table border="0" cellpadding="0" cellspacing="0" style="width: 100%; background-color:#ecf0f1">\n<tbody>\n<tr>\n<td>Currecy:</td>\n<td>{Contribution.currency}</td>\n</tr>\n<tr>\n<td>Organisation:</td>\n<td>{Account.shortName}</td>\n</tr>\n<tr>\n<td>Campagne Title:</td>\n<td>{Campaign.title}</td>\n</tr>\n<tr>\n<td>Donation Date:</td>\n<td>{Contribution.contributionDate}</td>\n</tr>\n<tr>\n<td>Your contribution ID:</td>\n<td>{Contribution.id}</td>\n</tr>\n<tr>\n<td>Frequency:</td>\n<td>{Contribution.frequency}</td>\n</tr>\n</tbody>\n</table>\n<p>\n<p>Mit Freundliche Grüße,\n\n<p>\n<table border="0" cellpadding="4" cellspacing="0">\n<tbody>\n<tr>\n<td style="text-align: center"><img alt="Ansprechpartner" src="{Campaign.contact.embeddedFoto}" style="border-radius: 50%; height:64px; width:64px" />\n<br>{Campaign.contact.firstName} {Campaign.contact.lastName}\n<br>Kampagnenleiter</td>\n<td>\n 🌐 {Campaign.url}\n<br>📞 <a href="tel:{Campaign.contact.phone}">{Campaign.contact.phone}</a>\n<br>📧 <a href="mailto:{Campaign.contact.email}">{Campaign.contact.email}</a>\n<br>💬 <a href="https://goodfunds.de">Chatte mit uns</a>\n</td>\n</tr>\n</tbody>\n</table>\n	Sie spenden {Contribution.amount} für TERRY Großartig.	\N	HTML
t	\N	222	130	2024-03-04 11:33:04.284274	DONATION	E-Mail, wenn ein Spender nun ein regelmäßiger Spender ist	Danke Mitgliedschaft	<p>{Contribution.getGermanSalutation("soft")}\n\n<p>fantastisch Sie wollen Mitglied bei <b>{Campaign.title}</b> werden. Ich freue mich, dass Sie sich entschieden haben, sich als Fördermitglied dauerhaft für die Verbreitung von Freiem Wissen einzusetzen.\n\n<p>Wir haben Ihre Anfrage zur Mitgliedschaft erhalten und freuen uns außerordentlich über Ihr Interesse, Teil unserer Gemeinschaft zu werden. Ihre Bereitschaft, nicht nur finanziell beizutragen, sondern auch als Mitglied aktiv teilzunehmen, erfüllt uns mit großer Freude.\n\n<p>Was passiert nun? In Kürze erhalten Sie weitere Informationen zu den Mitgliedschaftsmodalitäten und den Vorteilen, die damit verbunden sind. Wir schätzen Ihr Engagement und freuen uns darauf, gemeinsam mit Ihnen positive Veränderungen zu bewirken.\n\n<p>Mit Freundliche Grüße,\n<p>{Campaign.contact.firstName} {Campaign.contact.lastName}\n<p><hr>\n<table border="0" cellpadding="2" cellspacing="2">\n<tbody>\n<tr>\n<td><img alt="Campaign Image" src="{Campaign.embeddedLogo}" style="height:64px; width:64px" /></td>\n<td>\n{Campaign.title}\n<br>{Campaign.url}\n<br>{Campaign.contact.address}\n</td>\n</tr>\n</tbody>\n</table>\n<hr>	Ihr nachhaltiges Engagement als Fördermitglied bei {Account.accountName}	\N	HTML
t	2023-05-05 14:45:01.446976	223	130	2024-04-29 11:55:28.806554	PETITION	Email gesended als 2-schritt petition verfahren	Petition Bestetigung	<p>{Contribution.getGermanSalutation("formal")}\n\n<p>wir möchten uns herzlich bei Ihnen für Ihre wertvolle Unterstützung unserer Petition bedanken. Ihr Engagement ist von großer Bedeutung und trägt maßgeblich dazu bei, positive Veränderungen herbeizuführen.\n\n<p>Um sicherzustellen, dass Ihre Stimme zählt, bitten wir Sie freundlich, die Petition zu bestätigen. Dieser Schritt ist entscheidend, um die Wirksamkeit unserer Bemühungen zu gewährleisten und sicherzustellen, dass Ihre Meinung gehört wird.\n\n<br><br>\n<a style ="background-color: #199319;  color: white;  padding: 10px 15px;  text-decoration: none;" href="{Contribution.confirmationUrl}">Unterschrift Bestätigen</a>\n<br><br>\n\n<p>Ihre Unterstützung ist uns wichtig, und wir sind dankbar für Ihr Interesse an dieser Angelegenheit. Bitte nehmen Sie sich einen Moment Zeit, um die Bestätigung durchzuführen, damit wir gemeinsam einen positiven Einfluss erzielen können.\n\n<p><p>Vielen Dank für Ihr Verständnis und Ihre Mitarbeit.\n\n<p><p>Mit freundlichen Grüßen,\n\n<p><img alt="Campaign Image" src="{Campaign.contact.embeddedSignature}" style="height:64px; width:164px" />\n<p><p>{Campaign.contact.lastName} {Campaign.contact.firstName}\n<br>Geschäftsführer | {Account.shortName}\n<hr>\n{Account.contact.street} {Account.contact.postcode} {Account.contact.city}\n<br>Telf: {Account.contact.phone}\n<br>EMail: {Account.contact.email}\n\n<p><hr>\n<table border="0" cellpadding="4" cellspacing="0">\n<tbody>\n<tr>\n<td><img alt="Campaign Image" src="{Campaign.contact.embeddedFoto}" style="height:64px; width:64px" /></td>\n<td>\n{Campaign.contact.lastName} {Campaign.contact.firstName}\n<br>{Campaign.url}\n<br>{Campaign.contact.address}\n</td>\n</tr>\n</tbody>\n</table>\n<hr>	{Contribution.firstName}! Dank für Ihre Unterstützung und Erinnerung an die Petitionsbestätigung	\N	HTML
t	2023-05-22 12:38:20.115389	224	130	2024-03-04 13:18:06.613933	PETITION	E-Mail vorlag zum Unterschrift teilen	Unterschriftsteilen	Hallo liebe Freunde,\n\nich hoffe, diese Nachricht erreicht euch wohlauf. Kürzlich bin ich auf eine inspirierende Spendenkampagne gestoßen, die wirklich eine positive Veränderung bewirken kann. Die Organisation setzt sich für "{Campaign.description}" ein und hat bereits beeindruckende Fortschritte erzielt.\n\nIch dachte, ihr würdet euch ebenfalls für dieses wichtige Anliegen begeistern. Hier ist der Link zur Kampagne: \n\n{Campaign.url}\n\nJeder Beitrag zählt! Lasst uns gemeinsam Gutes tun und diese Initiative unterstützen.\n\nLiebe Grüße,\n{Contribution.firstName} {Contribution.lastName}	Entdeckte eine beeindruckende Spendenkampagne – Teilen für einen guten Zweck	\N	TEXT
f	2024-03-01 12:32:15.250241	225	130	2024-03-01 13:24:50.813471	DONATION	Test Qute template	Test-HTML	<p> {#if Contribution.salutation == 'MISTER'}\nSehr geehrter Herr {Contribution.lastName},\n{#else if Contribution.salutation == 'DAME'}\nSehr geehrte Frau {Contribution.lastName},\n{#else}\nSehr geehrte Damen und Herren,\n{/if}\n<p>\n<p>\n<p>{Campaign.city}\n<p>{Campaign.contact.address}\n<p>{Campaign.contact.city}\n<p>{Campaign.contact.countryName}\n<p>{Campaign.contact.country}\n<p>{Campaign.contact.email}\n<p>{Campaign.contact.embeddedFoto}\n<p>{Campaign.contact.embeddedSignature}\n<p>{Campaign.contact.firstName}\n<p>{Campaign.contact.getGermanSalutation("formal")}\n<p>{Campaign.contact.getGermanSalutation("soft")}\n<p>{Campaign.contact.id}\n<p>{Campaign.contact.isMain}\n<p>{Campaign.contact.lastName}\n<p>{Campaign.contact.mailSalutation}\n<p>{Campaign.contact.phone}\n<p>{Campaign.contact.postcode}\n<p>{Campaign.contact.salutation}\n<p>{Campaign.contact.street}\n<p>{Campaign.contact.title}\n<p>{Campaign.contact.type}\n<p>{Campaign.country}\n<p>{Campaign.descriptionShort}\n<p>{Campaign.description}\n<p>{Campaign.donationGoal}\n<p>{Campaign.donations}\n<p>{Campaign.image}\n<p>{Campaign.logo}\n<p>{Campaign.petitionGoal}\n<p>{Campaign.petitions}\n<p>{Campaign.postcode}\n<p>{Campaign.status}\n<p>{Campaign.street}\n<p>{Campaign.title}\n<p>{Campaign.totalDonations}\n<p>{Campaign.url}\n<p>{Campaign.youTubeVideoSrc}\n<p>{Contribution.address}\n<p>{Contribution.amount}\n<p>{Contribution.anonymousDonation}\n<p>{Contribution.authorizationId}\n<p>{Contribution.city}\n<p>{Contribution.comment}\n<p>{Contribution.company}\n<p>{Contribution.confirmationUrl}\n<p>{Contribution.contributionDate}\n<p>{Contribution.contributionType}\n<p>{Contribution.countryName}\n<p>{Contribution.country}\n<p>{Contribution.currency}\n<p>{Contribution.dedication}\n<p>{Contribution.donorAsCompany}\n<p>{Contribution.email}\n<p>{Contribution.feeAmount}\n<p>{Contribution.finalId}\n<p>{Contribution.firstName}\n<p>{Contribution.flagAndCountryName}\n<p>{Contribution.flagAndLocation}\n<p>{Contribution.frequency}\n<p>{Contribution.fullName}\n<p>{Contribution.getGermanSalutation("formal")}\n<p>{Contribution.getGermanSalutation("soft")}\n<p>{Contribution.goodFundsFee}\n<p>{Contribution.grossAmount}\n<p>{Contribution.internalNote}\n<p>{Contribution.lastName}\n<p>{Contribution.location}\n<p>{Contribution.orderId}\n<p>{Contribution.paymentMethod}\n<p>{Contribution.phone}\n<p>{Contribution.planID}\n<p>{Contribution.postcode}\n<p>{Contribution.prettyContributionDate}\n<p>{Contribution.salutation}\n<p>{Contribution.source}\n<p>{Contribution.statusChangeDate}\n<p>{Contribution.statusChangeNote}\n<p>{Contribution.status}\n<p>{Contribution.street}\n<p>{Contribution.subscriptionID}\n<p>{Contribution.subscriptionStatus}\n<p>{Contribution.title}\n<p>{Contribution.transactionId}\n<p>{Contribution.wantsMailing}	xxx	\N	HTML
f	2023-09-25 11:31:04.461576	226	130	2024-03-01 13:25:31.295881	DONATION	Test Qute template	Test-TEXT	 {#if Contribution.salutation == 'MISTER'}\nSehr geehrter Herr {Contribution.lastName},\n{#else if Contribution.salutation == 'DAME'}\nSehr geehrte Frau {Contribution.lastName},\n{#else}\nSehr geehrte Damen und Herren,\n{/if}\n\n\n{Campaign.city}\n{Campaign.contact.address}\n{Campaign.contact.city}\n{Campaign.contact.countryName}\n{Campaign.contact.country}\n{Campaign.contact.email}\n{Campaign.contact.embeddedFoto}\n{Campaign.contact.embeddedSignature}\n{Campaign.contact.firstName}\n{Campaign.contact.getGermanSalutation("formal")}\n{Campaign.contact.getGermanSalutation("soft")}\n{Campaign.contact.id}\n{Campaign.contact.isMain}\n{Campaign.contact.lastName}\n{Campaign.contact.mailSalutation}\n{Campaign.contact.phone}\n{Campaign.contact.postcode}\n{Campaign.contact.salutation}\n{Campaign.contact.street}\n{Campaign.contact.title}\n{Campaign.contact.type}\n{Campaign.country}\n{Campaign.descriptionShort}\n{Campaign.description}\n{Campaign.donationGoal}\n{Campaign.donations}\n{Campaign.image}\n{Campaign.logo}\n{Campaign.petitionGoal}\n{Campaign.petitions}\n{Campaign.postcode}\n{Campaign.status}\n{Campaign.street}\n{Campaign.title}\n{Campaign.totalDonations}\n{Campaign.url}\n{Campaign.youTubeVideoSrc}\n{Contribution.address}\n{Contribution.amount}\n{Contribution.anonymousDonation}\n{Contribution.authorizationId}\n{Contribution.city}\n{Contribution.comment}\n{Contribution.company}\n{Contribution.confirmationUrl}\n{Contribution.contributionDate}\n{Contribution.contributionType}\n{Contribution.countryName}\n{Contribution.country}\n{Contribution.currency}\n{Contribution.dedication}\n{Contribution.donorAsCompany}\n{Contribution.email}\n{Contribution.feeAmount}\n{Contribution.finalId}\n{Contribution.firstName}\n{Contribution.flagAndCountryName}\n{Contribution.flagAndLocation}\n{Contribution.frequency}\n{Contribution.fullName}\n{Contribution.getGermanSalutation("formal")}\n{Contribution.getGermanSalutation("soft")}\n{Contribution.goodFundsFee}\n{Contribution.grossAmount}\n{Contribution.internalNote}\n{Contribution.lastName}\n{Contribution.location}\n{Contribution.orderId}\n{Contribution.paymentMethod}\n{Contribution.phone}\n{Contribution.planID}\n{Contribution.postcode}\n{Contribution.prettyContributionDate}\n{Contribution.salutation}\n{Contribution.source}\n{Contribution.statusChangeDate}\n{Contribution.statusChangeNote}\n{Contribution.status}\n{Contribution.street}\n{Contribution.subscriptionID}\n{Contribution.subscriptionStatus}\n{Contribution.title}\n{Contribution.transactionId}\n{Contribution.wantsMailing}	xxx	\N	TEXT
\.


--
-- TOC entry 5113 (class 0 OID 16509)
-- Dependencies: 230
-- Data for Name: messagingprovider; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.messagingprovider (createdat, id, ownerid, updatedat, accountname, clientid, clientidtest, provider, secret, secrettest) FROM stdin;
2025-03-12 16:29:42.904361	203885631447040	\N	2025-03-17 16:07:25.236983	630920493431987	EAAI2CZAEfF7sBOwoZB0FjyZAbIWivqhI4kDNCCdbA6Un1B1yzNCJ4XIjBM1oX9BFYvZBcR3pILlHgDmdwlkQYDPIkTZAJN0c1iGizFHKiL7aeC6TZBhQeE24KYyZBUIyw4FBitlDHBa0xlXEhp3vLsRO9yp7V8CQpdxPtYx7BkQ5ytCA3aL0y9726ZBf1EDAHPqpMBGmiCr47cAu8zphytsrAZAlr	bischofsweg 94	WHATSAPP	GoodDEV	Plantaciones
2025-03-19 15:22:25.862475	206346356514816	\N	\N	zitrone-IG	555059617589837	-	INSTAGRAM	72144e43f1b1f308aeff676891d7c7e8	-
\.


--
-- TOC entry 5114 (class 0 OID 16516)
-- Dependencies: 231
-- Data for Name: p2pgroup_members; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.p2pgroup_members (p2pgroup_id, members) FROM stdin;
\.


--
-- TOC entry 5115 (class 0 OID 16519)
-- Dependencies: 232
-- Data for Name: p2pgroup_seenby; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.p2pgroup_seenby (p2pgroup_id, seenby) FROM stdin;
\.


--
-- TOC entry 5116 (class 0 OID 16522)
-- Dependencies: 233
-- Data for Name: p2pgroups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.p2pgroups (createdat, id, ownerid, updatedat, image, lastmessage, name) FROM stdin;
\.


--
-- TOC entry 5117 (class 0 OID 16529)
-- Dependencies: 234
-- Data for Name: p2pmessage_seenby; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.p2pmessage_seenby (p2pmessage_id, seenby) FROM stdin;
218812807794688	130
\.


--
-- TOC entry 5118 (class 0 OID 16532)
-- Dependencies: 235
-- Data for Name: p2pmessages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.p2pmessages (createdat, id, ownerid, reciverid, senderid, updatedat, message, senderimage, sendername) FROM stdin;
2025-04-23 20:48:33.067249	218812807794688	\N	199268075270145	130	\N	hallo wie geht es dir	\N	Sauer Zitrone
\.


--
-- TOC entry 5119 (class 0 OID 16539)
-- Dependencies: 236
-- Data for Name: prescription; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.prescription (quantity, adultid, calendarwhen, createdat, drug_id, id, ownerid, updatedat, calendarremind, calendarrepeat, contraindications, cronstring, description, dosage, indications) FROM stdin;
1	199268075270145	2025-03-09 15:37:00	2025-03-06 15:02:05.791961	201709941149697	201740731912193	\N	2025-03-10 15:35:21.047936	PT1M	WEEK_DAY	asdf	37 16 * * 1-5	Arnaldo	2	Plantaciones
\.


--
-- TOC entry 5120 (class 0 OID 16548)
-- Dependencies: 237
-- Data for Name: queries; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.queries (createdat, id, ownerid, updatedat, classname, description, partialwhere) FROM stdin;
\.


--
-- TOC entry 5121 (class 0 OID 16555)
-- Dependencies: 238
-- Data for Name: role_endpoints; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role_endpoints (role_id, endpoints) FROM stdin;
261	Roles
261	Users
261	PaymentMethods
261	Mailings
\.


--
-- TOC entry 5122 (class 0 OID 16558)
-- Dependencies: 239
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (istemplate, createdat, id, ownerid, updatedat, description, rolename, selectionmethod) FROM stdin;
t	2022-12-12 16:08:45.077	260	130	2024-03-04 13:57:27.697161	Der Master Nutzer hat die vollständige Kontrolle über die Anwendung	Master	NOT_SELECTED
t	2024-03-04 13:31:16.82967	261	130	2024-03-04 13:57:34.21461	Der Rolle Nutzer kann nicht die Anwendung konfigurieren	User	NOT_SELECTED
\.


--
-- TOC entry 5130 (class 0 OID 16714)
-- Dependencies: 247
-- Data for Name: tamagotchies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tamagotchies (id, createdat, ownerid, updatedat, age, alive, avatar, bedtime, cleanliness, currentemotion, energy, experience, generalemotion, happiness, health, hunger, level, name, sleeping, weight) FROM stdin;
219145598455808	2025-04-24 19:22:40.787261	\N	2025-04-24 19:25:33.704122	1	t	\N	19:00:00	95	🙂	85	30	2	45	90	5	1	yxcvb	f	50
\.


--
-- TOC entry 5123 (class 0 OID 16572)
-- Dependencies: 240
-- Data for Name: user_internalactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_internalactions (user_id, internalactions) FROM stdin;
\.


--
-- TOC entry 5124 (class 0 OID 16575)
-- Dependencies: 241
-- Data for Name: userpreferences; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.userpreferences (createdat, id, ownerid, updatedat, classname, view, "`view`") FROM stdin;
\.


--
-- TOC entry 5125 (class 0 OID 16582)
-- Dependencies: 242
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (failedattemptscount, isemailverified, isenabled, islive, ismfaexempt, istemporalpass, istotpregistred, rowsperpage, sessionid, createdat, id, lastsignin, messagingprovider_id, ownerid, role_id, updatedat, avatar, email, firstname, invitationrequesturl, lastname, password, phone, preferredlanguage, preferredtheme, status, type, username, usersecret, "`type`", account_id, messagingproviderid) FROM stdin;
0	t	t	f	t	f	f	22	-1	2023-06-21 14:40:55.880581	130	2025-04-24 19:23:21.105875	203885631447040	130	260	2025-04-24 19:23:21.106878	\N	Sitrone@simone.de	Sauer	\N	Zitrone	$2a$04$M2K6kiIkfsxqvHgl18/13OROOP3N3zqMgZgDHbTfktwz7LkDt/ZBe	+4915224625752	de	light	VERIFIED	CARER	Sitrone	ZRQJBAVOS3NUU3ERIGL2C37RBM74ZS7O	CARER	84233121845248	\N
\.


--
-- TOC entry 5126 (class 0 OID 16593)
-- Dependencies: 243
-- Data for Name: users_hannitasks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_hannitasks (user_id, tasks_id) FROM stdin;
130	199251063382016
\.


--
-- TOC entry 5127 (class 0 OID 16598)
-- Dependencies: 244
-- Data for Name: users_queries; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_queries (user_id, queries_id) FROM stdin;
\.


--
-- TOC entry 5128 (class 0 OID 16603)
-- Dependencies: 245
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_roles (user_id, associatedroles_id) FROM stdin;
130	260
\.


--
-- TOC entry 5129 (class 0 OID 16608)
-- Dependencies: 246
-- Data for Name: users_userpreferences; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_userpreferences (user_id, preferences_id) FROM stdin;
\.


--
-- TOC entry 4864 (class 2606 OID 16405)
-- Name: addresses addresses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (id);


--
-- TOC entry 4866 (class 2606 OID 16412)
-- Name: adults adults_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.adults
    ADD CONSTRAINT adults_pkey PRIMARY KEY (id);


--
-- TOC entry 4868 (class 2606 OID 16419)
-- Name: auditlogs auditlogs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auditlogs
    ADD CONSTRAINT auditlogs_pkey PRIMARY KEY (id);


--
-- TOC entry 4870 (class 2606 OID 16426)
-- Name: chatlogs chatlogs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chatlogs
    ADD CONSTRAINT chatlogs_pkey PRIMARY KEY (id);


--
-- TOC entry 4873 (class 2606 OID 16434)
-- Name: chatmessageentities chatmessageentities_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chatmessageentities
    ADD CONSTRAINT chatmessageentities_pkey PRIMARY KEY (id);


--
-- TOC entry 4876 (class 2606 OID 16441)
-- Name: countries countries_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.countries
    ADD CONSTRAINT countries_pkey PRIMARY KEY (id);


--
-- TOC entry 4878 (class 2606 OID 16448)
-- Name: drugs drugs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.drugs
    ADD CONSTRAINT drugs_pkey PRIMARY KEY (id);


--
-- TOC entry 4880 (class 2606 OID 16455)
-- Name: emojies emojies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emojies
    ADD CONSTRAINT emojies_pkey PRIMARY KEY (id);


--
-- TOC entry 4883 (class 2606 OID 16462)
-- Name: endpoints endpoints_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.endpoints
    ADD CONSTRAINT endpoints_pkey PRIMARY KEY (id);


--
-- TOC entry 4885 (class 2606 OID 16469)
-- Name: events events_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_pkey PRIMARY KEY (id);


--
-- TOC entry 4887 (class 2606 OID 16478)
-- Name: globalproperties globalproperties_confirmationcodeemail_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.globalproperties
    ADD CONSTRAINT globalproperties_confirmationcodeemail_id_key UNIQUE (confirmationcodeemail_id);


--
-- TOC entry 4889 (class 2606 OID 16480)
-- Name: globalproperties globalproperties_inviteusermail_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.globalproperties
    ADD CONSTRAINT globalproperties_inviteusermail_id_key UNIQUE (inviteusermail_id);


--
-- TOC entry 4891 (class 2606 OID 16476)
-- Name: globalproperties globalproperties_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.globalproperties
    ADD CONSTRAINT globalproperties_pkey PRIMARY KEY (id);


--
-- TOC entry 4893 (class 2606 OID 16482)
-- Name: globalproperties globalproperties_resetcredentialsmail_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.globalproperties
    ADD CONSTRAINT globalproperties_resetcredentialsmail_id_key UNIQUE (resetcredentialsmail_id);


--
-- TOC entry 4896 (class 2606 OID 16487)
-- Name: hannilogs hannilogs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hannilogs
    ADD CONSTRAINT hannilogs_pkey PRIMARY KEY (id);


--
-- TOC entry 4898 (class 2606 OID 16494)
-- Name: hannitasks hannitasks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hannitasks
    ADD CONSTRAINT hannitasks_pkey PRIMARY KEY (id);


--
-- TOC entry 4900 (class 2606 OID 16501)
-- Name: histories histories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.histories
    ADD CONSTRAINT histories_pkey PRIMARY KEY (id);


--
-- TOC entry 4902 (class 2606 OID 16508)
-- Name: mailings mailings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mailings
    ADD CONSTRAINT mailings_pkey PRIMARY KEY (id);


--
-- TOC entry 4904 (class 2606 OID 16515)
-- Name: messagingprovider messagingprovider_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messagingprovider
    ADD CONSTRAINT messagingprovider_pkey PRIMARY KEY (id);


--
-- TOC entry 4906 (class 2606 OID 16528)
-- Name: p2pgroups p2pgroups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.p2pgroups
    ADD CONSTRAINT p2pgroups_pkey PRIMARY KEY (id);


--
-- TOC entry 4908 (class 2606 OID 16538)
-- Name: p2pmessages p2pmessages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.p2pmessages
    ADD CONSTRAINT p2pmessages_pkey PRIMARY KEY (id);


--
-- TOC entry 4910 (class 2606 OID 16547)
-- Name: prescription prescription_drug_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prescription
    ADD CONSTRAINT prescription_drug_id_key UNIQUE (drug_id);


--
-- TOC entry 4912 (class 2606 OID 16545)
-- Name: prescription prescription_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prescription
    ADD CONSTRAINT prescription_pkey PRIMARY KEY (id);


--
-- TOC entry 4914 (class 2606 OID 16554)
-- Name: queries queries_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.queries
    ADD CONSTRAINT queries_pkey PRIMARY KEY (id);


--
-- TOC entry 4916 (class 2606 OID 16564)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 4935 (class 2606 OID 16720)
-- Name: tamagotchies tamagotchies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tamagotchies
    ADD CONSTRAINT tamagotchies_pkey PRIMARY KEY (id);


--
-- TOC entry 4918 (class 2606 OID 16581)
-- Name: userpreferences userpreferences_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userpreferences
    ADD CONSTRAINT userpreferences_pkey PRIMARY KEY (id);


--
-- TOC entry 4927 (class 2606 OID 16597)
-- Name: users_hannitasks users_hannitasks_tasks_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_hannitasks
    ADD CONSTRAINT users_hannitasks_tasks_id_key UNIQUE (tasks_id);


--
-- TOC entry 4921 (class 2606 OID 16590)
-- Name: users users_messagingprovider_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_messagingprovider_id_key UNIQUE (messagingprovider_id);


--
-- TOC entry 4923 (class 2606 OID 16588)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 4929 (class 2606 OID 16602)
-- Name: users_queries users_queries_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_queries
    ADD CONSTRAINT users_queries_pkey PRIMARY KEY (user_id, queries_id);


--
-- TOC entry 4925 (class 2606 OID 16592)
-- Name: users users_role_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_role_id_key UNIQUE (role_id);


--
-- TOC entry 4931 (class 2606 OID 16607)
-- Name: users_roles users_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, associatedroles_id);


--
-- TOC entry 4933 (class 2606 OID 16612)
-- Name: users_userpreferences users_userpreferences_preferences_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_userpreferences
    ADD CONSTRAINT users_userpreferences_preferences_id_key UNIQUE (preferences_id);


--
-- TOC entry 4871 (class 1259 OID 16613)
-- Name: chatmessageentities_memoryid; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX chatmessageentities_memoryid ON public.chatmessageentities USING btree (memoryid);


--
-- TOC entry 4874 (class 1259 OID 16614)
-- Name: countries_code; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX countries_code ON public.countries USING btree (code);


--
-- TOC entry 4881 (class 1259 OID 16615)
-- Name: endpoints_ownerid; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX endpoints_ownerid ON public.endpoints USING btree (ownerid);


--
-- TOC entry 4894 (class 1259 OID 16616)
-- Name: hannilogs_ownerid; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX hannilogs_ownerid ON public.hannilogs USING btree (ownerid);


--
-- TOC entry 4919 (class 1259 OID 16617)
-- Name: user_username; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX user_username ON public.users USING btree (username);


--
-- TOC entry 4940 (class 2606 OID 16638)
-- Name: p2pgroup_seenby fk16d5oq7iiy15s2mrwqikjarry; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.p2pgroup_seenby
    ADD CONSTRAINT fk16d5oq7iiy15s2mrwqikjarry FOREIGN KEY (p2pgroup_id) REFERENCES public.p2pgroups(id);


--
-- TOC entry 4951 (class 2606 OID 16698)
-- Name: users_roles fk2iuayi31a6uniqaesbullkrmm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fk2iuayi31a6uniqaesbullkrmm FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4936 (class 2606 OID 16623)
-- Name: globalproperties fk638padv04uop1d1sgvfjenq5k; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.globalproperties
    ADD CONSTRAINT fk638padv04uop1d1sgvfjenq5k FOREIGN KEY (inviteusermail_id) REFERENCES public.mailings(id);


--
-- TOC entry 4943 (class 2606 OID 16653)
-- Name: role_endpoints fk6vv5lvlyfqj3xotsgn6dkn935; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_endpoints
    ADD CONSTRAINT fk6vv5lvlyfqj3xotsgn6dkn935 FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 4953 (class 2606 OID 16703)
-- Name: users_userpreferences fk71f8moxwn50gxa2swq0uiejr8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_userpreferences
    ADD CONSTRAINT fk71f8moxwn50gxa2swq0uiejr8 FOREIGN KEY (preferences_id) REFERENCES public.userpreferences(id);


--
-- TOC entry 4945 (class 2606 OID 16663)
-- Name: users fk93t6fsabomo6w41ksaj3009a7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk93t6fsabomo6w41ksaj3009a7 FOREIGN KEY (messagingprovider_id) REFERENCES public.messagingprovider(id);


--
-- TOC entry 4947 (class 2606 OID 16673)
-- Name: users_hannitasks fkbym4kx330cl6ippfrf001sk72; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_hannitasks
    ADD CONSTRAINT fkbym4kx330cl6ippfrf001sk72 FOREIGN KEY (tasks_id) REFERENCES public.hannitasks(id);


--
-- TOC entry 4946 (class 2606 OID 16668)
-- Name: users fkcogjq1smjy03v5s2wfegritx6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkcogjq1smjy03v5s2wfegritx6 FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 4942 (class 2606 OID 16648)
-- Name: prescription fkdmwagj4ngrs92o8pggynmfg55; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prescription
    ADD CONSTRAINT fkdmwagj4ngrs92o8pggynmfg55 FOREIGN KEY (drug_id) REFERENCES public.drugs(id);


--
-- TOC entry 4948 (class 2606 OID 16678)
-- Name: users_hannitasks fkebhuoluqjiockqbno9aja8snt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_hannitasks
    ADD CONSTRAINT fkebhuoluqjiockqbno9aja8snt FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4939 (class 2606 OID 16633)
-- Name: p2pgroup_members fkikrejqclf7vubbic0ftb8241x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.p2pgroup_members
    ADD CONSTRAINT fkikrejqclf7vubbic0ftb8241x FOREIGN KEY (p2pgroup_id) REFERENCES public.p2pgroups(id);


--
-- TOC entry 4941 (class 2606 OID 16643)
-- Name: p2pmessage_seenby fklybvhrwj2vaoweanw1gl9fuyx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.p2pmessage_seenby
    ADD CONSTRAINT fklybvhrwj2vaoweanw1gl9fuyx FOREIGN KEY (p2pmessage_id) REFERENCES public.p2pmessages(id);


--
-- TOC entry 4937 (class 2606 OID 16618)
-- Name: globalproperties fkm26koa259blsh7877clenx89; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.globalproperties
    ADD CONSTRAINT fkm26koa259blsh7877clenx89 FOREIGN KEY (confirmationcodeemail_id) REFERENCES public.mailings(id);


--
-- TOC entry 4938 (class 2606 OID 16628)
-- Name: globalproperties fkm75bm1kv779pvfbwm1aaf3q73; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.globalproperties
    ADD CONSTRAINT fkm75bm1kv779pvfbwm1aaf3q73 FOREIGN KEY (resetcredentialsmail_id) REFERENCES public.mailings(id);


--
-- TOC entry 4949 (class 2606 OID 16688)
-- Name: users_queries fkrhl9tx7otlh3gmq4m09dnrwcd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_queries
    ADD CONSTRAINT fkrhl9tx7otlh3gmq4m09dnrwcd FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4952 (class 2606 OID 16693)
-- Name: users_roles fkris0x2awl9i8txb3jamvdrd03; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fkris0x2awl9i8txb3jamvdrd03 FOREIGN KEY (associatedroles_id) REFERENCES public.roles(id);


--
-- TOC entry 4950 (class 2606 OID 16683)
-- Name: users_queries fks0k3ds4pe7g364bjxw6r0tclj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_queries
    ADD CONSTRAINT fks0k3ds4pe7g364bjxw6r0tclj FOREIGN KEY (queries_id) REFERENCES public.queries(id);


--
-- TOC entry 4944 (class 2606 OID 16658)
-- Name: user_internalactions fksp9gv9f2h52xfr1pn4qujsc1k; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_internalactions
    ADD CONSTRAINT fksp9gv9f2h52xfr1pn4qujsc1k FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4954 (class 2606 OID 16708)
-- Name: users_userpreferences fksw6v6j3w16wt5rkro1oj8n0k; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_userpreferences
    ADD CONSTRAINT fksw6v6j3w16wt5rkro1oj8n0k FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2025-04-24 21:27:24

--
-- PostgreSQL database dump complete
--

-- Completed on 2025-04-24 21:27:24

--
-- PostgreSQL database cluster dump complete
--

