--
-- PostgreSQL database dump
--

-- Dumped from database version 10.0
-- Dumped by pg_dump version 10.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: plv8; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plv8 WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plv8; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plv8 IS 'PL/JavaScript (v8) trusted procedural language';


--
-- Name: btree_gin; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS btree_gin WITH SCHEMA public;


--
-- Name: EXTENSION btree_gin; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION btree_gin IS 'support for indexing common datatypes in GIN';


--
-- Name: btree_gist; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS btree_gist WITH SCHEMA public;


--
-- Name: EXTENSION btree_gist; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION btree_gist IS 'support for indexing common datatypes in GiST';


--
-- Name: citext; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS citext WITH SCHEMA public;


--
-- Name: EXTENSION citext; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION citext IS 'data type for case-insensitive character strings';


--
-- Name: cube; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS cube WITH SCHEMA public;


--
-- Name: EXTENSION cube; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION cube IS 'data type for multidimensional cubes';


--
-- Name: dblink; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS dblink WITH SCHEMA public;


--
-- Name: EXTENSION dblink; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION dblink IS 'connect to other PostgreSQL databases from within a database';


--
-- Name: dict_int; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS dict_int WITH SCHEMA public;


--
-- Name: EXTENSION dict_int; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION dict_int IS 'text search dictionary template for integers';


--
-- Name: dict_xsyn; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS dict_xsyn WITH SCHEMA public;


--
-- Name: EXTENSION dict_xsyn; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION dict_xsyn IS 'text search dictionary template for extended synonym processing';


--
-- Name: earthdistance; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS earthdistance WITH SCHEMA public;


--
-- Name: EXTENSION earthdistance; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION earthdistance IS 'calculate great-circle distances on the surface of the Earth';


--
-- Name: fuzzystrmatch; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS fuzzystrmatch WITH SCHEMA public;


--
-- Name: EXTENSION fuzzystrmatch; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION fuzzystrmatch IS 'determine similarities and distance between strings';


--
-- Name: hstore; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS hstore WITH SCHEMA public;


--
-- Name: EXTENSION hstore; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION hstore IS 'data type for storing sets of (key, value) pairs';


--
-- Name: intarray; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS intarray WITH SCHEMA public;


--
-- Name: EXTENSION intarray; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION intarray IS 'functions, operators, and index support for 1-D arrays of integers';


--
-- Name: ltree; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS ltree WITH SCHEMA public;


--
-- Name: EXTENSION ltree; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION ltree IS 'data type for hierarchical tree-like structures';


--
-- Name: pg_stat_statements; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pg_stat_statements WITH SCHEMA public;


--
-- Name: EXTENSION pg_stat_statements; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pg_stat_statements IS 'track execution statistics of all SQL statements executed';


--
-- Name: pg_trgm; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pg_trgm WITH SCHEMA public;


--
-- Name: EXTENSION pg_trgm; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pg_trgm IS 'text similarity measurement and index searching based on trigrams';


--
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


--
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


--
-- Name: pgrowlocks; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pgrowlocks WITH SCHEMA public;


--
-- Name: EXTENSION pgrowlocks; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgrowlocks IS 'show row-level locking information';


--
-- Name: pgstattuple; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pgstattuple WITH SCHEMA public;


--
-- Name: EXTENSION pgstattuple; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgstattuple IS 'show tuple-level statistics';


--
-- Name: postgis; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA public;


--
-- Name: EXTENSION postgis; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION postgis IS 'PostGIS geometry, geography, and raster spatial types and functions';


--
-- Name: tablefunc; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS tablefunc WITH SCHEMA public;


--
-- Name: EXTENSION tablefunc; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION tablefunc IS 'functions that manipulate whole tables, including crosstab';


--
-- Name: unaccent; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS unaccent WITH SCHEMA public;


--
-- Name: EXTENSION unaccent; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION unaccent IS 'text search dictionary that removes accents';


--
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


--
-- Name: xml2; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS xml2 WITH SCHEMA public;


--
-- Name: EXTENSION xml2; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION xml2 IS 'XPath querying and XSLT';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clothing_item; Type: TABLE; Schema: public; Owner: wzlscuud
--

CREATE TABLE public.clothing_item (
    item_id integer NOT NULL,
    type_id integer,
    color character varying(6) NOT NULL,
    size character varying(2) NOT NULL,
    quantity integer NOT NULL,
    gender boolean
);


ALTER TABLE public.clothing_item OWNER TO wzlscuud;

--
-- Name: clothing_type; Type: TABLE; Schema: public; Owner: wzlscuud
--

CREATE TABLE public.clothing_type (
    type_id integer NOT NULL,
    name character varying(20) NOT NULL,
    category character varying(10) NOT NULL,
    price numeric(6,2) NOT NULL,
    description character varying(255) NOT NULL
);


ALTER TABLE public.clothing_type OWNER TO wzlscuud;

--
-- Name: outfit; Type: TABLE; Schema: public; Owner: wzlscuud
--

CREATE TABLE public.outfit (
    outfit_id integer NOT NULL,
    creator character varying(40),
    full_body boolean,
    top integer,
    bottom integer,
    jacket integer,
    profile character varying(40)
);


ALTER TABLE public.outfit OWNER TO wzlscuud;

--
-- Name: profile; Type: TABLE; Schema: public; Owner: wzlscuud
--

CREATE TABLE public.profile (
    email character varying(40) NOT NULL,
    password character varying(60) DEFAULT '$2a$10$oVu37exhYEklinIBoB9lGefiZnFroU7ZwJDWhQ7.902.8XYSB2mYS'::character varying NOT NULL,
    size_shirt character varying(2),
    size_pants character varying(2),
    size_dress character varying(2)
);


ALTER TABLE public.profile OWNER TO wzlscuud;

--
-- Name: wishlist; Type: TABLE; Schema: public; Owner: wzlscuud
--

CREATE TABLE public.wishlist (
    email character varying(40) NOT NULL,
    item_id integer NOT NULL
);


ALTER TABLE public.wishlist OWNER TO wzlscuud;

--
-- Data for Name: clothing_item; Type: TABLE DATA; Schema: public; Owner: wzlscuud
--

COPY public.clothing_item (item_id, type_id, color, size, quantity, gender) FROM stdin;
157162639	285499569	101010	m	48	\N
227983723	285499569	404040	xl	16	\N
308272705	285499569	A0A0A0	s	42	\N
428469077	285499569	DCDCDC	l	33	f
475667343	285499569	707070	xs	47	f
419774328	285499569	808080	s	3	t
395556840	285499569	202020	m	11	\N
105096825	285499569	F5F5F5	xl	25	\N
397961372	720211709	D3D3D3	m	35	\N
489168872	720211709	000000	m	50	\N
172537499	720211709	101010	l	5	\N
200769938	720211709	303030	xs	5	t
498873841	720211709	A0A0A0	m	16	\N
197838189	720211709	202020	s	39	f
278130913	395557109	404040	s	50	f
377401028	395557109	707070	xl	27	t
257059328	395557109	202020	s	26	\N
759748311	395557109	FFFFFF	s	15	\N
656178382	395557109	F5F5F5	l	32	\N
285594393	395557109	D3D3D3	l	6	f
611555792	395557109	808080	xl	3	\N
955633659	660115502	202020	s	6	f
425714804	660115502	707070	s	12	f
621306085	660115502	808080	s	27	t
923522890	660115502	606060	l	31	t
856809905	660115502	D3D3D3	xl	12	\N
237186765	660115502	101010	xs	44	t
666991694	660115502	B0B0B0	xl	45	t
882183569	660115502	000000	l	42	\N
520528095	709054560	303030	l	46	\N
911484894	709054560	808080	xs	28	\N
273158956	709054560	D3D3D3	s	50	t
894384124	709054560	101010	xl	22	f
340145590	709054560	909090	xs	18	f
469469879	709054560	DCDCDC	xl	41	f
866903534	709054560	505050	xs	7	f
963998576	709054560	000000	xs	32	f
741717425	674932943	FFFFFF	xl	4	\N
104732181	674932943	202020	xl	15	\N
990129055	674932943	B0B0B0	xs	33	t
352504239	674932943	909090	l	24	\N
226842274	674932943	808080	s	28	t
412615539	674932943	505050	l	17	\N
648224293	674932943	606060	l	27	\N
680244946	674932943	F5F5F5	s	15	t
187787746	674932943	D3D3D3	xl	23	\N
965313081	240698130	909090	8	24	f
514977669	240698130	808080	2	11	f
408764041	240698130	505050	12	50	f
227116032	240698130	A0A0A0	6	5	f
444509158	240698130	101010	6	12	f
344974212	240698130	404040	8	18	f
789920365	240698130	FFFFFF	8	16	f
209779897	250879861	F5F5F5	xs	23	f
578398693	250879861	505050	s	36	t
207882780	250879861	707070	s	4	t
132760216	250879861	FFFFFF	xs	31	t
426490988	250879861	808080	s	38	\N
383595010	250879861	303030	xl	12	\N
123901496	250879861	404040	m	18	f
122798119	250879861	101010	xs	49	t
513287384	849825435	303030	m	33	f
144500573	849825435	606060	xs	48	f
525939884	849825435	505050	m	9	\N
251688780	849825435	D3D3D3	s	7	f
806196095	849825435	000000	xs	23	\N
922306327	849825435	F5F5F5	xl	26	\N
589022206	430627026	D3D3D3	l	12	f
303595348	430627026	303030	xl	23	f
282637946	430627026	C0C0C0	xl	22	t
744139029	430627026	707070	xs	16	t
448933502	430627026	A0A0A0	m	10	\N
773112126	430627026	101010	s	7	\N
710404565	430627026	F5F5F5	l	48	\N
215967891	430627026	606060	xs	4	t
814323172	720120373	707070	l	49	\N
279048847	720120373	202020	xs	34	\N
927037806	720120373	B0B0B0	xs	40	f
857693453	720120373	606060	l	30	t
586652318	720120373	A0A0A0	xl	23	t
804883399	720120373	FFFFFF	l	45	f
525500757	720120373	C0C0C0	xs	31	f
628269465	836673326	A0A0A0	s	43	t
266081530	836673326	404040	l	27	t
749350787	836673326	000000	s	8	t
316682698	836673326	303030	xs	7	\N
584236079	836673326	909090	s	36	t
317166232	836673326	606060	s	26	f
472565616	836673326	505050	l	27	\N
120501328	836673326	B0B0B0	m	41	f
775339839	468276714	C0C0C0	14	8	f
477632185	468276714	FFFFFF	12	16	f
989252333	468276714	202020	0	14	f
653969645	468276714	404040	2	33	f
768790761	468276714	707070	0	20	f
665871636	468276714	B0B0B0	8	44	f
998090545	468276714	606060	10	40	f
827294564	468276714	000000	8	45	f
973634549	140226300	101010	xl	26	\N
602586981	140226300	000000	xs	39	\N
878178222	140226300	404040	xl	31	\N
223787697	140226300	303030	m	25	t
628658146	140226300	FFFFFF	xl	38	t
668092668	140226300	606060	xs	43	\N
235053689	140226300	505050	s	13	t
789611275	140226300	707070	l	23	\N
626634608	859609403	DCDCDC	xs	14	t
699786808	859609403	C0C0C0	s	3	t
535557521	859609403	303030	xl	21	t
718942972	859609403	808080	l	10	t
403977321	859609403	000000	s	10	t
502888880	859609403	F5F5F5	xs	33	f
839623509	859609403	FFFFFF	m	50	\N
585367446	859609403	A0A0A0	s	47	t
161656306	859609403	B0B0B0	m	26	f
345083245	859609403	606060	s	43	t
248488429	457136344	707070	xs	36	f
944477624	457136344	DCDCDC	m	50	\N
949978304	457136344	FFFFFF	xs	43	f
867864849	457136344	F5F5F5	m	24	t
276586129	457136344	101010	s	25	f
691222494	457136344	202020	s	26	f
676496609	457136344	404040	xl	20	\N
946481895	374581910	606060	12	37	f
610126729	374581910	F5F5F5	4	36	f
462275944	374581910	A0A0A0	8	24	f
915190867	374581910	DCDCDC	10	25	f
776367075	374581910	404040	6	17	f
523987947	374581910	202020	2	33	f
428856485	374581910	808080	8	34	f
219522897	374581910	101010	0	37	f
932979563	998342202	505050	m	43	t
235615391	998342202	707070	m	50	t
985389900	998342202	404040	m	39	f
534301619	998342202	000000	xl	37	t
579181229	998342202	606060	m	35	\N
736585779	998342202	D3D3D3	xl	7	\N
278187893	998342202	303030	l	31	\N
470613853	998342202	C0C0C0	m	47	f
469796440	998342202	A0A0A0	m	28	\N
499759944	659004047	F5F5F5	xs	15	\N
255888877	659004047	909090	l	33	f
532405322	659004047	303030	l	25	f
144564470	659004047	B0B0B0	l	41	t
239023583	659004047	808080	s	45	f
575540869	659004047	707070	l	11	t
732934517	659004047	FFFFFF	xs	25	\N
986043684	756741519	D3D3D3	xs	48	\N
468244242	756741519	B0B0B0	xl	13	t
391995266	756741519	404040	m	16	f
675377318	756741519	FFFFFF	s	48	\N
203286364	756741519	808080	m	11	\N
666770459	756741519	606060	xs	9	\N
106612299	765158210	D3D3D3	12	38	f
136814280	765158210	B0B0B0	8	28	f
780859603	765158210	606060	14	31	f
376678331	765158210	707070	2	19	f
215909072	765158210	C0C0C0	8	17	f
521168086	765158210	101010	14	31	f
327920787	765158210	000000	8	22	f
518762376	765158210	505050	10	19	f
848638183	765158210	404040	2	37	f
898189139	887162937	A0A0A0	l	50	t
402749930	887162937	000000	s	36	\N
943912652	887162937	101010	xl	8	\N
526464409	887162937	303030	m	20	\N
504400365	887162937	808080	xs	14	f
352737576	887162937	505050	s	37	t
105061361	887162937	404040	m	26	f
870641011	887162937	909090	s	34	\N
260205534	887162937	FFFFFF	xl	16	f
950659258	842239534	000000	l	23	f
956544109	842239534	404040	xs	13	\N
213610659	842239534	DCDCDC	xs	44	f
628280733	842239534	505050	m	48	f
235643437	842239534	707070	xs	34	t
750347915	842239534	B0B0B0	xs	15	f
335533295	842239534	A0A0A0	l	38	t
674239245	842239534	606060	m	14	t
143798692	130416315	F5F5F5	xs	29	f
398439233	130416315	FFFFFF	xs	31	t
863330146	130416315	D3D3D3	m	17	\N
881985428	130416315	303030	l	12	t
927125119	130416315	606060	l	20	\N
582113601	130416315	202020	l	40	\N
654532223	130416315	808080	l	22	f
914037323	130416315	A0A0A0	xl	42	f
535201684	524635841	DCDCDC	4	39	f
702167401	524635841	404040	14	23	f
502515536	524635841	F5F5F5	10	7	f
830008107	524635841	909090	0	27	f
580420144	524635841	101010	6	11	f
627769945	524635841	000000	0	10	f
544230233	524635841	C0C0C0	12	21	f
410932300	524635841	303030	14	19	f
961939175	524635841	B0B0B0	4	22	f
542707853	565956011	707070	8	20	f
437777077	565956011	505050	10	24	f
464771940	565956011	202020	14	7	f
192474873	565956011	A0A0A0	8	14	f
995235119	565956011	404040	10	42	f
164461912	565956011	808080	14	30	f
205788636	565956011	303030	12	45	f
295744850	565956011	606060	0	11	f
642450012	565956011	000000	4	17	f
765991858	293534086	505050	s	26	t
331023725	293534086	404040	l	43	f
120025381	293534086	000000	m	29	t
228938221	293534086	909090	xs	16	f
430445945	293534086	B0B0B0	l	26	t
946179995	293534086	C0C0C0	s	49	\N
184749066	293534086	D3D3D3	xl	29	t
799800239	293534086	808080	l	12	f
\.


--
-- Data for Name: clothing_type; Type: TABLE DATA; Schema: public; Owner: wzlscuud
--

COPY public.clothing_type (type_id, name, category, price, description) FROM stdin;
285499569	Padded	Jacket	83.00	A an ice cold padded jacket that's surprisingly warm
720211709	Solid	Sweatpants	49.99	The GroutFit staple. For hitting the gym or the entire pint of ice cream.
395557109	Graphic	Sweatshirt	93.00	Our classic Sweatshirt with custom made graphics that cycle every month.
660115502	Pin - Striped	Pants	58.00	Are you talkin to me? Are YOU talkin to ME?
709054560	Woven	Jacket	97.00	A lightweight woven cotton jacket. Breathes in Spring, insulates in Fall.
674932943	Two - Tone	Jeans	39.99	Sturdy jeans, twice the fun
240698130	Denim	Skirt	55.00	Punk rock never went out of style. Not included: studs
250879861	Block	T - Shirt	74.99	A great basic t-shirt for keeping things interesting without overwhelming the outfit.
849825435	Dyed	Jeans	63.99	Dyed jeans in many shades! Grey, gray, white, and black.
430627026	Casual	Shirt	91.95	Our staple non-tucked button down. Perfect for a night out.
720120373	Side - Striped	Sweatpants	93.00	All day I dream about sweatpants.
836673326	Solid	Pants	73.99	An elegant classic, for a more formal time.
468276714	Lattice	Dress	49.95	A perfect, lightweight, spring dress that wears nicely with bright accents.
859609403	Solid	Sweatshirt	83.95	Our basic hoodie in all shades of gray.
457136344	Solid	T - Shirt	22.95	Our basic t-shirt in all shades of gray.
374581910	Solid	Dress	48.00	Our basic dress in all shades of gray.
998342202	Striped	Shirt	43.99	Shake up your formal wear with a simple pattern to create interest.
659004047	Acid Washed	Jeans	98.00	Far out, man (We removed the acid, your legs are safe)
756741519	Graphic	T - Shirt	98.95	Custom made art graphics that cycle once per month. 
887162937	Eggshell	Shirt	61.00	An easy pattern that creates interest up close, but washes out from a distance.
842239534	Textured	Sweatpants	24.95	Perfect for winter. A sturdier cotton blend provides more insulation.
130416315	Dotted	Shirt	73.95	Five piece polka marching band unfortunately NOT included.
524635841	Solid	Skirt	40.95	Our basic skirt in 20 shades of gray.
565956011	Striped	Dress	59.00	A minimal, classic pattern on a modern cut dress.
293534086	Striped	T - Shirt	85.99	Take me out to the balllll, gaaaame.
140226300	Distressed	T - Shirt	49.00	To make all your older relatives ask \\"Did you buy it like that?\\"
765158210	Floral	Dress	44.00	Nothing says \\"floral\\" like 20 shades of gray.
\.


--
-- Data for Name: outfit; Type: TABLE DATA; Schema: public; Owner: wzlscuud
--

COPY public.outfit (outfit_id, creator, full_body, top, bottom, jacket, profile) FROM stdin;
\.


--
-- Data for Name: profile; Type: TABLE DATA; Schema: public; Owner: wzlscuud
--

COPY public.profile (email, password, size_shirt, size_pants, size_dress) FROM stdin;
TEST@TEST.COM	test	\N	\N	\N
TiphanyNMuldon@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	l	0
FloridaEMicah@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	xs	10
WenonahCHynes@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	m	6
JorrieRTheone@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	l	\N
EmmiLeontina@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	s	0
RandeeVolnay@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	s	\N
WalliwAmbrose@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	xl	2
EmeliaHook@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	s	4
AnitaMKirschner@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	l	10
MirelleReedy@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	l	\N
KimmieErmeena@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	s	\N
PhebeJarita@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	m	10
KyleFIoyal@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	l	\N
TraceyEtem@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	m	\N
TovaAMelburn@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xs	2
FiliaPEnrika@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xs	\N
ZoeERess@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	m	\N
TiffanieHClara@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xl	\N
XavieraDDiet@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	s	\N
LexyJColeman@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	xl	10
CarolinEGar@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	l	14
NicoleImojean@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	s	\N
JaymeWEarley@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	m	6
StaciaCWayland@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xl	\N
MarciGCalise@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	xl	\N
EvaleenChappie@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	m	\N
HoratiaDWulf@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	xl	\N
AudryeSchuman@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	l	8
CatharinaChantalle@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	xl	\N
DeidrePantin@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	m	0
TallulahARhiamon@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	s	10
ChristianaBalbinder@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	m	0
ChloetteGone@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	l	\N
MireielleLRempe@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	m	\N
JenniHFries@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xl	\N
ChristinaKGoodspeed@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	s	14
MelbaGaulin@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xs	\N
JolyMunster@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	m	14
AureliaColan@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	xl	\N
JacquiOTuck@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	l	2
AnatolaPorcia@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	m	\N
CarittaBrothers@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	xl	2
ZariaRanitta@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	l	0
SybilaMagdau@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	m	\N
LisettaGrefe@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	m	10
NestaFHenni@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xl	8
AlikeeWDelora@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xl	\N
JackquelineKSpencer@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	xl	\N
BlancaGVernon@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	s	\N
MellyLepp@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xs	6
AdeliceLLilllie@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	s	6
AnaPadraic@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	xl	\N
EmmaGBotti@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	l	6
PageLOthella@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xl	\N
HolliCoffin@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	m	\N
DyanBittner@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	m	\N
GwennyEHaugen@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xl	\N
CatharinaStevie@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	s	4
DorrisARichards@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	m	14
BeaHEdana@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xs	\N
SibbieStout@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xs	10
AilaWGoss@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	xl	2
DebraMJareb@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	m	\N
TressaIBraswell@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xl	2
test@matt.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	s	s
MirelleMarthena@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	xl	0
HerminiaQueston@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	s	4
PeriWinfred@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xl	10
LorySSirotek@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	s	0
BrennaBSteere@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	l	\N
GustiVBrook@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	xl	10
VernaFBailar@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xl	8
KerstinLGarett@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xs	\N
CindelynTamaru@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	l	2
EvelineMeakem@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	l	\N
IdaliaGMariette@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	s	14
WilmaRhonda@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	xl	\N
PattyCHelfant@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	m	\N
DomenigaPharaoh@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	l	\N
KymDJorgenson@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	s	\N
IvieSomerville@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xl	14
AngelaHunsinger@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	xl	\N
MerlaOmor@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	xl	\N
LucretiaRDygal@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	l	\N
RosyEthe@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	m	6
RozalieHLambertson@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	s	\N
AnjelaFrisse@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	m	\N
JasminaNickey@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	s	\N
KaliHOcko@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	l	8
CelestiaEAlonso@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	l	6
DottieOAmador@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	l	14
JoelleBenjamen@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	m	\N
NorrieMGrannie@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	s	14
KitFaulkner@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	xl	\N
TildaSBarry@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	l	14
RodiGabriela@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	l	\N
DitaBobbye@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	m	\N
ModestiaMcDougall@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	l	4
FlossiBowers@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	s	\N
JustineTAdrea@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	s	\N
IbbieTLiberati@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	s	0
PippaBVerdie@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	xl	\N
EliciaCMariel@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xl	4
JolynKunz@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	s	10
NattyBPasadis@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	xs	\N
AngelinaKial@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	l	\N
TerrijoQuickman@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	m	\N
EveleenBelmonte@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	m	6
TeresaVeal@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	m	\N
JinnyRHoran@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xl	10
blah@test.com	$2a$10$SW.ZdTb1Zcp0ETseqGlRLODN4tYewCfEccO5h97EUZzfX3KQngwXq	\N	\N	\N
john	$2a$10$jYrP/ZpOn301KBF2UefDyu56V3Jhn8MZsehlS7ZNrKd2iOCB9JxTe	\N	\N	\N
spencerjrakoczy	$2a$10$bT.pzwz.FPQfG0HKwkqwfulGkPiD0dO/XjFtpQ8zxlhD8B4o60ire	\N	\N	\N
spencerrakoczy@gmail.com	$2a$10$5SZZKHA0uo/ZYxnZ0dFLXuEmwnNkSFCWAfKN/xpSfhf7uI.YkT/aW	\N	\N	\N
blam@test.com	$2a$10$rU4dRwyxwzCjyDGnIj4Yse3k4x9XcwSR0QorrbR6DUyKsVKi4nZXm	\N	\N	\N
tester1	$2a$10$JzkMBoS7Pb/W9LxxFM2DPekSjLh3LJvWJ.pM91Zwm1Cjv0PwqArwm	\N	\N	\N
hello0	$2a$10$ni1QY6y2i2qGiIqPGDUKDeEq3.MCdJap/LqBApQKZmlihi5JdU5ae	\N	\N	\N
spankydoodle	$2a$10$kJCpSYREpaYtUFOW5JfTI.WTzY7yStxkfO8T3OiM4WXIAtLBNkkKW	\N	\N	\N
blart@test.com	$2a$10$zL.SeOOvQr0ajoe3.7RhV.gyo8GFdupoU2M.pXEPDgLIrbjstyA62	\N	\N	\N
sprankledangle	$2a$10$KKWZNk/0XvqZScn8HmBzd.0ZgSuCEp6dHXDj.1mAvxi65smfdTs62	\N	\N	\N
chang@cs.pitt.edu	$2a$10$YJOw52qN9waFZi4r/u5OC.uPkozL52uGJw3.9Mn/N4JIu3On2f4b2	\N	\N	\N
aky13@pitt.edu	$2a$10$8be7YdpGpHuzshmfXQiIUuA4PwN7PVWs8A8ZzSqsVnaEYcXsAB1/S	\N	\N	\N
GlyndaRGauldin@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	xl	\N
SalomaHDesimone@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	m	10
FanniOMariska@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	s	\N
CoreenBPattie@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	xl	12
KarylIRind@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	m	\N
RoselinISonny@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	s	\N
MirelleEolanda@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	l	14
ClaireTeferi@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xs	2
CelestineCorotto@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	l	\N
FerneArdella@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	l	14
SuzanneMerta@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	s	\N
JackiWSerrell@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	m	\N
LarissaPTunnell@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	m	6
AbbeyBogey@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	l	14
GnniABert@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	l	2
LeontineAgripina@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xl	10
CherianneOTella@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	xs	4
CharylMatias@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	l	\N
CarlyAtthia@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	xs	\N
HelsaCampbell@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xs	\N
AmandyBSchweiker@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	m	\N
KarilFavien@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xs	2
SusanettaEolande@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	l	\N
BertyYPell@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	xs	10
KizzeeKraft@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	xs	\N
LucineAhouh@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	m	4
NadyTHasseman@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	s	14
ElseyFederico@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	s	12
CassaundraSPolard@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	l	\N
AnnabelleKElyssa@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xs	2
JaneenEphraim@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	xl	\N
VikiEgan@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	s	\N
NoveliaSPalila@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	m	8
CamilaCPleasant@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xl	14
FloraCharpentier@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	xs	\N
JemmieSIsleana@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	m	4
TressaEdmunda@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xl	\N
DarynShenan@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	l	0
RozeleXMcIntyre@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xs	6
JennineCinelli@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xs	2
MelittaAMoskow@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	m	8
TishVAurora@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	xs	\N
JillanaBBerlinda@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	s	\N
AdelindO'Malley@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	s	2
JaquelinJaret@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	l	\N
NonaPhemia@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	xs	\N
BeckyEIsle@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	m	4
KymESchenck@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	s	\N
EdivaSherrill@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xs	8
GertiMurdock@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	l	4
MarrileePGemina@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	xl	\N
AlbinaABrunhild@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	s	\N
CelinkaRSiegel@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	l	\N
IdellePrimo@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	l	\N
OlympeMarylin@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xs	10
KerstinOWayland@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xs	10
LeoraGerrard@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	l	\N
DelcinaJammin@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xl	\N
GermaineQLukash@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xl	\N
IrinaZima@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	s	\N
IngabergRizzo@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	m	\N
JanettaBSaimon@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	s	2
MarielMLiu@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	xl	\N
OllyMWitty@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	xl	14
BibbieAbbottson@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	l	2
KellieAJanessa@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	l	\N
AimilBevash@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	l	2
GillyClary@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	xs	\N
KellenHardej@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	m	4
LethiaOCelestine@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	xl	\N
LynnelleLHuggins@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xs	\N
SheenaCPrussian@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	xl	6
MarielEberle@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	s	0
KirstynBReich@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	s	\N
KarineNedra@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	xs	\N
RodinaDToddy@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	m	2
RodiArianna@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	s	\N
SheelaThamos@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	m	\N
GenevieveRonel@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	s	l	\N
NealaSPurdy@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xs	6
AllaImogene@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	xl	8
CristyICychosz@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	l	\N
spankybaby	$2a$10$v4wh1.3UyScQhXF2c9kvd.mwtKyLkr2SgfbaiZMHcVpt1/ss1QZda	\N	\N	\N
AmelieAOrji@outlook.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	l	\N
ElaneEKatinka@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xl	xs	4
AnissaChantal@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	xl	10
KelsyLRosette@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	m	8
SharonRMinier@iCloud.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	m	0
JacquelinGonzalez@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	l	xl	\N
KalieGFugate@gmail.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	s	10
VivieneCZaremski@yahoo.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	m	l	\N
BertiLambrecht@aol.com	$2a$10$cAEE6wo3RYSWoEUMsj0eBej9a9y0EOOL2dhAqF2oYpZ7uGQ5fRf32	xs	s	10
spencerrakoczy	$2a$10$x8DBfUakthQPZBNMoP2xXelju52.oSzh4T/hq1z7fyubz8HsF/UQO	\N	\N	\N
hellloboy	$2a$10$X4eX2Z3bHziYuJCV/s0r2OllKHHSr3yTr7ymJV3B12hzLrod8GWZC	\N	\N	\N
\.


--
-- Data for Name: spatial_ref_sys; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.spatial_ref_sys (srid, auth_name, auth_srid, srtext, proj4text) FROM stdin;
\.


--
-- Data for Name: wishlist; Type: TABLE DATA; Schema: public; Owner: wzlscuud
--

COPY public.wishlist (email, item_id) FROM stdin;
\.


--
-- Name: clothing_item clothing_item_pkey; Type: CONSTRAINT; Schema: public; Owner: wzlscuud
--

ALTER TABLE ONLY public.clothing_item
    ADD CONSTRAINT clothing_item_pkey PRIMARY KEY (item_id);


--
-- Name: clothing_type clothing_type_pkey; Type: CONSTRAINT; Schema: public; Owner: wzlscuud
--

ALTER TABLE ONLY public.clothing_type
    ADD CONSTRAINT clothing_type_pkey PRIMARY KEY (type_id);


--
-- Name: outfit outfit_pkey; Type: CONSTRAINT; Schema: public; Owner: wzlscuud
--

ALTER TABLE ONLY public.outfit
    ADD CONSTRAINT outfit_pkey PRIMARY KEY (outfit_id);


--
-- Name: profile profile_pkey; Type: CONSTRAINT; Schema: public; Owner: wzlscuud
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (email);


--
-- Name: wishlist wishlist_pkey; Type: CONSTRAINT; Schema: public; Owner: wzlscuud
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT wishlist_pkey PRIMARY KEY (email, item_id);


--
-- Name: clothing_item clothing_item_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: wzlscuud
--

ALTER TABLE ONLY public.clothing_item
    ADD CONSTRAINT clothing_item_type_id_fkey FOREIGN KEY (type_id) REFERENCES public.clothing_type(type_id);


--
-- Name: outfit fk_profile; Type: FK CONSTRAINT; Schema: public; Owner: wzlscuud
--

ALTER TABLE ONLY public.outfit
    ADD CONSTRAINT fk_profile FOREIGN KEY (profile) REFERENCES public.profile(email);


--
-- Name: outfit outfit_bottom_fkey; Type: FK CONSTRAINT; Schema: public; Owner: wzlscuud
--

ALTER TABLE ONLY public.outfit
    ADD CONSTRAINT outfit_bottom_fkey FOREIGN KEY (bottom) REFERENCES public.clothing_item(item_id);


--
-- Name: outfit outfit_creator_fkey; Type: FK CONSTRAINT; Schema: public; Owner: wzlscuud
--

ALTER TABLE ONLY public.outfit
    ADD CONSTRAINT outfit_creator_fkey FOREIGN KEY (creator) REFERENCES public.profile(email);


--
-- Name: outfit outfit_jacket_fkey; Type: FK CONSTRAINT; Schema: public; Owner: wzlscuud
--

ALTER TABLE ONLY public.outfit
    ADD CONSTRAINT outfit_jacket_fkey FOREIGN KEY (jacket) REFERENCES public.clothing_item(item_id);


--
-- Name: outfit outfit_top_fkey; Type: FK CONSTRAINT; Schema: public; Owner: wzlscuud
--

ALTER TABLE ONLY public.outfit
    ADD CONSTRAINT outfit_top_fkey FOREIGN KEY (top) REFERENCES public.clothing_item(item_id);


--
-- Name: wishlist wishlist_email_fkey; Type: FK CONSTRAINT; Schema: public; Owner: wzlscuud
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT wishlist_email_fkey FOREIGN KEY (email) REFERENCES public.profile(email);


--
-- Name: wishlist wishlist_item_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: wzlscuud
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT wishlist_item_id_fkey FOREIGN KEY (item_id) REFERENCES public.clothing_item(item_id);


--
-- PostgreSQL database dump complete
--

